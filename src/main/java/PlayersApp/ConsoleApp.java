package PlayersApp;

import PlayersApp.Model.Player;
import PlayersApp.Model.Response;
import PlayersApp.Model.ResponseInt;
import PlayersApp.Model.Statuses;
import PlayersApp.service.PlayerService;
import PlayersApp.service.PlayerServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleApp {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        printCommands();

        PlayerService service = new PlayerServiceImpl();

        while (true)
        {
            try {
                System.out.println("enter the command:");
                String input = scanner.nextLine();
                if(!checkCommand(input))
                {
                    System.out.println("unknown command: " + input + ". Enter \"help\" to view commands");
                }

                if (input.equalsIgnoreCase("quit")) {
                    System.out.println("good bye!");
                    System.exit(0);
                }

                if (input.equalsIgnoreCase("help")) {
                    printCommands();
                }

                if (input.equalsIgnoreCase("list")) {
                    var players = service.getPlayers();
                    if (players.getPayload().isEmpty()) {
                        System.out.println("list is empty");
                    }
                    for (Player player : players.getPayload()) {
                        System.out.println(player);
                    }
                }

                if (input.startsWith("add")) {
                    String nick = input.substring(4);
                    Response<Integer> response = service.createPlayer(nick);
                    if(response.getStatus().equals(Statuses.OK))
                        System.out.println(response.getMessage());
                    else
                        System.err.println(response.getMessage());
                }

                if (input.startsWith("get")) {
                    String idAsString = input.substring(4);
                    int id = Integer.parseInt(idAsString);
                    Player player = service.getPlayerById(id).getPayload();
                    System.out.println(player);
                }

                if (input.startsWith("delete")) {
                    String idAsString = input.substring(7);
                    int id = Integer.parseInt(idAsString);
                    Player player = service.deletePlayerById(id).getPayload();
                    System.out.println(player.getNick() + " is deleted");
                }

                if (input.startsWith("points")) {
                    String params = input.substring(7);
                    String[] paramsArray = params.split(" ");

                    int id = Integer.parseInt(paramsArray[0]);
                    int points = Integer.parseInt(paramsArray[1]);
                    Response<Integer> response = service.addPoints(id, points);
                    if(response.getStatus().equals(Statuses.OK)) {
                        System.out.println(response.getMessage());
                        System.out.println(Statuses.OK.getCode());
                        System.out.println(Statuses.OK.getComment());
                    }
                    else
                        System.err.println(response.getMessage());
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void printCommands() {
        System.out.println("help - description commands");
        System.out.println("quit - exit");
        System.out.println("list - getAllPlayers");
        System.out.println("add <nickname> - create player by nickname");
        System.out.println("delete <id> - delete player by id");
        System.out.println("get <id> - get player by id");
        System.out.println("points <id><points> - add points to player by id");
        System.out.println();
    }

    private static boolean checkCommand(String command)
    {
        return (command.equalsIgnoreCase("help")
                || command.equalsIgnoreCase("quit")
                || command.equalsIgnoreCase("list")
                || command.startsWith("add")
                || command.startsWith("delete")
                || command.startsWith("get")
                || command.startsWith("points"));
    }
}