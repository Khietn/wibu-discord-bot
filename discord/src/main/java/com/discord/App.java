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
		final String token = "++++++ODg4MDcwNjUyODU3Mjg2NjY3.GwZi0m.kfm0Xs44WxIAH1ORE5LmyL5MN9B_W7bS_TYh3U";
		DiscordClient client = DiscordClient.create(token.replace("+",""));
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
