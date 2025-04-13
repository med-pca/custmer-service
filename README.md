# 📦 Customer Refund Management - Spring Boot Application

Ce projet est une application backend Spring Boot permettant :

#### User Story

> As a customer, I can view the history of my orders.\
> I can submit a refund request for a specific product.

#### User Story

> For refund requests, I should provide evidence like picture and issue description.


---

## 🛠️ Prérequis

- Java 21
- Maven 3.8+
- IDE (IntelliJ, VSCode…)
- Postman 

---

## 🚀 Lancer l’application

```bash
./mvnw spring-boot:run
# ou
mvn clean install
mvn spring-boot:run
```
---

## 📬 Endpoints principaux


### Récupérer l'historique de commandes d'un client
```http
GET http://localhost:8086/api/customers/{id}/orders
Content-Type: application/json


```

### Soumettre une demande de remboursement
```http
POST http://localhost:8086/api/customers/{id}/refund
Content-Type: application/json

{
    "reason": "Produit endommagé",
    "imageUrl": "https://example.com/image.jpg",
    "orderProductId": 3
}
```


✅ Fonctionnalités
Mapping DTO via MapStruct
Exceptions centralisées via @RestControllerAdvice
Tests unitaires avec JUnit 5 & Mockito


⚠️ Gestion des erreurs
Les erreurs sont renvoyées au format JSON structuré :

{
  "timestamp": "2025-04-12T16:45:55.123",
  "status": 404,
  "error": "Resource not found",
  "message": "OrderProduct not found with id: 15"
}