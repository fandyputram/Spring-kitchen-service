# Final Project PBKK 
# Kitchen Service

* 5116100076 - Rifqi Mukti
* 5116100080 - Fandy Putra Mohammad
* 5116100185 - Nabil Haidarrahman    
---

End Point
---
### Order
| Method |      End Point     |       Parameter       |       Description      |
|:------:|:------------------:|:---------------------:|:----------------------:|
|  POST  |       /order       | detail, price, status |    Create new Order    |
|   GET  |       /order       |           -           |     Get all Orders     |
|   GET  |     /order/{id}    |           -           | Get Order detail by id |
|   GET  | /order/{id}/status |           -           |    Get Order status    |
|   PUT  |     /order/{id}    |         status        |   Update Order status  |
|   GET  |       /status      |           -           |  Get available status  |
|   GET  |    /status/{id}    |           -           |    Get status by id    |
