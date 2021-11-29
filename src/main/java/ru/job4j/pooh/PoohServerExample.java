package ru.job4j.pooh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Class PoohServerExample
 *
 *  КАРКАС ПРИМЕР СЕРВЕРА
 *
 *  Проверим работу сервера через cURL.
 *  1. запустить сервер PoohServer
 *  2. Открыть CMD и перейти :  cd C:\Tools\curl773\bin
 *  3. Проверить работоспособность curl: curl -V
 *  4. Отправить запрос: curl -X POST -d "text=13" http://localhost:9000/queue/weather
 *  5. Ответ должен быть: text=13
 *  (все работает)
 *
 *
 * @author Kseniya Dergunova
 * @since 28.11.2021
 */
public class PoohServerExample {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     InputStream input = socket.getInputStream()) {
                    byte[] buff = new byte[1_000_000];
                    var total = input.read(buff);
                    var text = new String(Arrays.copyOfRange(buff, 0, total), StandardCharsets.UTF_8);
                    System.out.println(text);
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    out.write(text.getBytes());
                }
            }
        }
    }
}
