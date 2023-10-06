package fact.it.race.service;

import fact.it.race.dto.CircuitResponse;
import fact.it.race.dto.RaceRequest;
import fact.it.race.dto.TeamResponse;
import fact.it.race.model.Race;
import fact.it.race.model.RaceTeam;
import fact.it.race.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RaceService {

    private final RaceRepository raceRepository;
    private final WebClient webClient;

    public boolean createRace(RaceRequest raceRequest) {
        Race race = new Race();
        race.setId(UUID.randomUUID().toString());

        List<RaceTeam> raceTeams = raceRequest.getRaceTeamDtoList()
                .stream()
                .map(this::mapToRaceTeam)
                .toList();

        race.setRaceTeamList(raceTeams);

        List<String> names = race.getRaceTeamList().stream()
                .map(RaceTeam::getName)
                .toList();

        TeamResponse[] teamResponseArray = webClient.get()
                .uri("http://localhost:8082/api/team",
                        uriBuilder -> uriBuilder.queryParam("name", names).build())
                .retrieve()
                .bodyToMono(TeamResponse[].class)
                .block();

        CircuitResponse[] circuitResponseArray = webClient.get()
                .uri("http://localhost:8080/api/circuit",
                        uriBuilder -> uriBuilder.queryParam("name", names).build())
                .retrieve()
                .bodyToMono(CircuitResponse[].class)
                .block();

        race.getRaceTeamList().stream()
                .map(raceTeam -> {
                    CircuitResponse circuit = Arrays.stream(productResponseArray)
                            .filter(p -> p.getSkuCode().equals(orderItem.getSkuCode()))
                            .findFirst()
                            .orElse(null);
                    if (product != null) {
                        orderItem.setPrice(product.getPrice());
                    }
                    return orderItem;
                })
                .collect(Collectors.toList());

        orderRepository.save(order);
        return true;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(order -> new OrderResponse(
                        order.getOrderNumber(),
                        mapToOrderLineItemsDto(order.getOrderLineItemsList())
                ))
                .collect(Collectors.toList());
    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }

    private List<OrderLineItemDto> mapToOrderLineItemsDto(List<OrderLineItem> orderLineItems) {
        return orderLineItems.stream()
                .map(orderLineItem -> new OrderLineItemDto(
                        orderLineItem.getId(),
                        orderLineItem.getSkuCode(),
                        orderLineItem.getPrice(),
                        orderLineItem.getQuantity()
                ))
                .collect(Collectors.toList());
    }
}