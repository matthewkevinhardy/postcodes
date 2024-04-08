package postcode;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.transaction.PlatformTransactionManager;

import postcode.documents.Postcode;
import postcode.documents.Ward;
import postcode.repos.PostcodeRepo;
import postcode.repos.WardRepo;

@SpringBootApplication(scanBasePackages = { "postcode" })
public class BatchImport {
	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(BatchImport.class, args)));
	}

	@Value("classpath:/data/ONSPD_NOV_2022_UK.csv")
	private Resource postcodeFile;

	@Value("classpath:/data/Ward names and codes UK as at 12_22_NSPD.csv")
	private Resource wardFile;

	@Autowired
	private WardRepo wardRepo;

	@Autowired
	private PostcodeRepo postcodeRepo;

	@Bean
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration.builder().connectedTo("localhost:9200").build();
	}

	@Bean
	public Job importCsvJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

		Step postcodeStep = importStep(jobRepository, transactionManager,
				reader(Postcode.class,
						new String[] { "pcd", "pcd2", "pcds", "dointr", "doterm", "oscty", "ced", "oslaua", "osward",
								"parish" },
						new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, postcodeFile),
				writer(postcodeRepo), postcodeProcessor());

		Step wardStep = importStep(jobRepository, transactionManager,
				reader(Ward.class, new String[] { "WD22CD", "WD22NM" }, new int[] { 0, 1 }, wardFile), writer(wardRepo),
				null);

		return new JobBuilder("importCsvJob", jobRepository).start(wardStep).next(postcodeStep).build();
	}

	/**
	 * 
	 * @param <T>
	 * @param type
	 * @param jobRepository
	 * @param transactionManager
	 * @param reader
	 * @param writer
	 * @param postcodeProcessor
	 * @return
	 */
	private <T> Step importStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			ItemReader<T> reader, ItemWriter<T> writer, ItemProcessor<T, T> itemProcessor) {

		return new StepBuilder("importStep", jobRepository).<T, T>chunk(1000, transactionManager)
				.processor(itemProcessor).reader(reader).writer(writer).build();
	}

	/**
	 * 
	 * @return
	 */
	private ItemProcessor<Postcode, Postcode> postcodeProcessor() {
		return new ItemProcessor<Postcode, Postcode>() {

			@Override
			public Postcode process(Postcode item) throws Exception {
				item.setPcd(item.getPcd().replaceAll("\\s", "").toLowerCase());
				return item;
			}
		};
	}

	/**
	 * 
	 * @param <T>
	 * @param type
	 * @param names
	 * @param includeFields
	 * @return
	 */
	private <T> ItemReader<T> reader(Class<T> type, String[] fieldNames, int[] includeFields, Resource inputFile) {

		FlatFileItemReader<T> reader = new FlatFileItemReader<>();

		reader.setResource(inputFile);

		reader.setLinesToSkip(1);

		reader.setLineMapper(new DefaultLineMapper<T>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(fieldNames);
						setIncludedFields(includeFields);
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<T>() {
					{
						setTargetType(type);
					}
				});
			}
		});

		return reader;
	}

	private <T, ID> ItemWriter<T> writer(ElasticsearchRepository<T, ID> repo) {
		return new ItemWriter<T>() {

			@Override
			public void write(Chunk<? extends T> chunk) throws Exception {
				repo.saveAll(chunk);
			}
		};
	}
}