package com.discord;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import reactor.core.publisher.Mono;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		final String token = "ODg4MDcwNjUyODU3Mjg2NjY3.GAVXKQ.jBIfbwjs9bedjpSy3blkYu7eJlE-wG_W3JI4Jc";
		DiscordClient client = DiscordClient.create(token);
		Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) ->
	    gateway.on(MessageCreateEvent.class, event -> {
	      Message message = event.getMessage();
	      System.out.println("Test: " + 	      event.getMessage().getData().content());
	      if (message.getContent().equalsIgnoreCase("ping")) {
	          return message.getChannel()
	              .flatMap(channel -> channel.createMessage("Ban co cac khong!"));
	      }

	      return Mono.empty();
	    }));
		login.block();
	}

}
