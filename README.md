# Project-EnterpriceDevelopment-Team12
Michiel Van Loy
Bent Melis
Subject: Formula 1

API GATEWAY URL:
https://api-gateway-bmelis.cloud.okteto.net

VIDEO URL:
https://www.youtube.com/watch?v=9QtyGEKMavc

![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/ac4091aa-7308-47ac-a994-e5648a80b202)

**Een woordje uitleg over project:**

Als onderwerp van ons project hebben we voor F1 gekozen, hiervoor hebben we een microservice van race, circuit, team en driver. Deze services zullen gebruikt worden om voorspellingen te doen over het winnend team van een specifieke race. Jammer genoeg zijn we er niet tot gekomen om een front-end te maken voor de gebruiker om dit op een gebruiksvriendelijke manier te doen.

De structuur van de microservices kan u zien in onderstaande screenshot. Wat u daar ziet is dat een race bestaat uit 1 circuit en 1 team, en dat een bepaald team bestaat uit 2 drivers.

De overkoepelende microservice is de API gateway service, deze zorgt er eigenlijk voor dat niet iedereen nieuwe voorspellingen kan aanmaken of verwijderen etc. Dit wordt gedaan aan de hand van aan bearer token die de gebruiker moet meegeven. In de races microservice kun je een voorspelling doen met POST, bewerken met UPDATE en verwijderen met DELETE. Natuurlijk is het ook mogelijk alle voorspellingen op te halen met een getAll, maar ook een specifieke met getById. In circuits is er dan weer de mogelijkheid om alle circuits op te halen met een getAll of een specifiek circuit door de getById. Ook in de microservice van teams is er de mogelijk om zowel alle team als één specifiek team op te halen met getAll en getById. Als laatst is er dan de drivers microservice waar er de mogelijkheid is om alle drivers van een bepaald team op te halen met de getByTeamId.

**Extra toevoegingen:**

2.2 Kubernetes Manifest .yml
Als uitbereiding op het project hebben we ervoor gekozen de docker-compose.yml om te zetten naar een Kubernetes Manifest .yml file. Dit hebben we gedaan omdat we dit in het verleden ook al eens hebben moeten doen ne er dus bekend mee waren.

**Race:**

getAll (/race):
![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/e46908e4-fdeb-4ffc-a91f-3fc23a8683fc)

getById (/race/x):
![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/0fc0b6e7-6a88-4836-84ee-09e53ca16b2a)

Post (/race)
![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/b21e61f6-be17-41aa-a513-1261446ef268)

Update (/race/x):
![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/fbf4907f-a4ba-4cf4-9787-23bcfde595b1)

Delete (/race/x)
![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/7fbae426-15e4-4c9c-8108-5427dbf22080)


**Circuit:**

getAll (/circuit)
![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/fa0b497b-794b-49d3-9bc6-befe374d4858)

getById (/circuit/x)
![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/7edb6995-d880-465c-9182-f456a11718c5)


**Team:**

getAll (/team)
![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/625f15e2-f427-422c-b164-66be9b8c6044)

getById(/team/x)
![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/b6fdacf7-ba8f-479c-9492-8c5473ea894a)


**Driver:**

getDriverByTeamId (/driver/x)
![image](https://github.com/bmelis/Project-EnterpriseDevelopment-Team12/assets/71631709/f54987ec-b8f5-44a9-8c00-3b48ce25048d)



