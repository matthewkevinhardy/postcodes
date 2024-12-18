# Postcodes Webservice
Imports ward, parish and postcode data from CSV files into an ElasticSearch index.

Postcode data available from [The Office For National Statistics](https://geoportal.statistics.gov.uk/search?q=PRD_ONSPD%20FEB_2024&sort=Date%20Created%7Ccreated%7Cdesc)

## Batch Import
CSV data imported using Spring Batch:

`java -jar batch/target/batch-0.0.1-SNAPSHOT.jar`

## Postcode Service
Data from ElasticSearch made available via a webservice hosted within Spring Boot Starter Web:

`java -jar service/target/service-0.0.1-SNAPSHOT.jar`

### Ward endpoints
- [https://mkhardy.co.uk/postcodes/v1/ward/E05013934](https://mkhardy.co.uk/postcodes/v1/ward/E05013934)
- [https://mkhardy.co.uk/postcodes/v1/ward?name=lane](https://mkhardy.co.uk/postcodes/v1/ward?name=lane)

### Parish endpoints
- [https://mkhardy.co.uk/postcodes/v1/parish/E43000254](https://mkhardy.co.uk/postcodes/v1/parish/E43000254)
- [https://mkhardy.co.uk/postcodes/v1/parish?name=end](https://mkhardy.co.uk/postcodes/v1/parish?name=end)

### Postcode endpoints
- [https://mkhardy.co.uk/postcodes/v1/postcode/tw27yl](https://mkhardy.co.uk/postcodes/v1/postcode/tw27yl)
- [https://mkhardy.co.uk/postcodes/v1/postcode?ward=E05011430](https://mkhardy.co.uk/postcodes/v1/postcode?ward=E05011430)
