# Postcodes Webservice
[Office For National Statistics](https://geoportal.statistics.gov.uk/search?q=PRD_ONSPD%20FEB_2024&sort=Date%20Created%7Ccreated%7Cdesc)

## Batch Import
CSV data imported using Spring Batch:

`java -jar batch/target/batch-0.0.1-SNAPSHOT.jar`

## Postcode Service
Data from ElasticSearch made available via a webservice hosted within Spring Boot Starter Web:

`java -jar service/target/service-0.0.1-SNAPSHOT.jar`

### Ward endpoint
### Parish endpoint
### Postcode endpoint
