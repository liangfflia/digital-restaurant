package com.drestaurant.restaurant.domain

import org.axonframework.commandhandling.CommandHandler

import org.axonframework.eventhandling.EventBus
import org.axonframework.eventhandling.GenericEventMessage.asEventMessage
import org.axonframework.modelling.command.AggregateNotFoundException
import org.axonframework.modelling.command.Repository

internal open class RestaurantCommandHandler(private val repository: Repository<Restaurant>, private val eventBus: EventBus) {

    @CommandHandler
    fun handle(command: ValidateOrderByRestaurantInternalCommand) = try {
        repository.load(command.restaurantId.identifier).execute { it.validateOrder(command.targetAggregateIdentifier, command.lineItems, command.auditEntry) }
    } catch (exception: AggregateNotFoundException) {
        eventBus.publish(asEventMessage<Any>(RestaurantNotFoundForOrderInternalEvent(command.restaurantId, command.targetAggregateIdentifier, command.auditEntry)))
    }
}
