# ChatBot

This is a simple chatbot.

# Functionality 
1. Copies your text message and send it to you

# How to Configure

First of all - you need to create a group in VK. 

Next step - Go to Settings of your just created group and make API Token for interaction with your app. Also, you need to enable messages from the community (check official VK API guide).

Now you need to past API Token in application.properties.

After all of that - use ngrock to interact with VK. (check ngrock guide, you just need to set the auth Token and server's port). You need to past the selected port in an application.properties (server.port).

Then ngrock successfully started, you need to confirm your server for VK. Again, go to Setting, API section, Callback API, Server settings, set API to 5.50, copy expected response,  past it in application.properties. Run project, and press the Confirm button in group settings.

Congratulations, you successfully configured your project!

# Technologies

1. Spring Boot
2. Spring MVC
3. Lombok
4. Gson
5. RestTemplate
6. MockMVC

