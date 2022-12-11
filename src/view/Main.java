package view;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String KEY_TO_EXIT = "q";
		Scanner scanner = new Scanner(System.in);
		String message = "";

		ClientConnection connection = new ClientConnection();

		while (!message.equals(KEY_TO_EXIT)) {
			System.err.println("Digite q para parar o programa ");
			System.out.println("Deseja buscar uma cidade pelo nome ou já conhece seu id? nome/id");
			message = scanner.nextLine();
			if (message.contentEquals("id")) {
				System.out.println("Digite o id da cidade desejada: ");
				String cityId = scanner.nextLine();
					if(cityId.equals(KEY_TO_EXIT)){
						break;
					}

				connection.setCityId(Integer.parseInt(cityId));

				connection.getSevenDays();
				connection.getPrevisao();

				} else if (message.contentEquals("nome")) {
				System.out.println("Digite o nome da cidade desejada: ");
				String cityName = scanner.nextLine();
					if(cityName.equals(KEY_TO_EXIT)){
						break;
					}

				connection.setCityName(cityName);
				connection.searchName();

				System.out.println("Digite o id da cidade desejada: ");
				String cityId = scanner.nextLine();
						if(cityId.equals(KEY_TO_EXIT)){
							break;
						}
				connection.setCityId(Integer.parseInt(cityId));

				connection.getSevenDays();
				connection.getPrevisao();
				}

			}
		scanner.close();
	}
}