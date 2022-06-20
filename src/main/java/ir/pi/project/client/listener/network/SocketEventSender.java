package ir.pi.project.client.listener.network;


import ir.pi.project.client.listener.EventSender;
import ir.pi.project.shared.event.Event;
import ir.pi.project.shared.gson.Deserializer;
import ir.pi.project.shared.gson.Serializer;
import ir.pi.project.shared.response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SocketEventSender implements EventSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;

    public SocketEventSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Event.class, new Serializer<>())
                .create();
    }

    @Override
    public Response send(Event event) {
        String eventString = gson.toJson(event, Event.class);
        printStream.println(eventString);
        String responseString = null;
            try {
                responseString = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("sever is out of access!");
                e.printStackTrace();
            }

        return gson.fromJson(responseString, Response.class);
    }

    @Override
    public void close() {
        try {
            printStream.close();
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
