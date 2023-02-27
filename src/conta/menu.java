package conta;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class menu {

	public static void main(String[] args) {
        
		Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
           
           ContaController contas = new  ContaController();
           
			int opcao;
			int agencia, tipo, aniversario, numero, numeroDestino;
			String titular;
			float saldo, limite, valor;
			
			
			System.out.println("\nCriar Contas\n");

			ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
			contas.cadastrar(cc1);

			ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
			contas.cadastrar(cc2);

			ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
			contas.cadastrar(cp1);

			ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
			contas.cadastrar(cp2);

			contas.listarTodas();

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);
			
			try {
			    opcao = sc.nextInt();
			}catch(InputMismatchException e ) {
				System.out.println("Digite valores inteiros!");
				sc.nextLine();
				opcao=0;
			}

            if (opcao == 9) {
                System.out.println("\nBanco do Brazil com z - 0 seu Futuro começa aqui!");
                sc.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:                 
                	System.out.println("Criar Conta\n\n");
                	System.out.println("Número da Agencia: ");
					agencia = sc.nextInt();
					System.out.println("Nome do Titular: ");
					sc.skip("\\R?");
					titular =  sc.nextLine();
                	do {
                        System.out.println("Tipo da Conta (1-CC / 2-CP:  ");
                        tipo = sc.nextInt();
                    }while (tipo < 1 && tipo > 2);

                    System.out.println("Saldo da Conta: ");
                    saldo = sc.nextFloat();

                    switch (tipo) {
                    case 1 -> {
                        System.out.println("Limite da Conta Corrente: ");
                        limite = sc.nextFloat();
                        contas.cadastrar(new ContaCorrente(contas.gerarNumero(),agencia, tipo, titular, saldo, limite));
                    }
                    case 2 -> {
                        System.out.println("Aniversário da Conta Poupança: ");
                        aniversario = sc.nextInt();
                        contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
                    }
                }
                    keyPress();
                    break;
                case 2:
                    System.out.println("2- Listar todas Contas");
                    contas.listarTodas();                 
                    keyPress();
                    break;
                case 3:
                    System.out.println("3- Buscar Conta por Numero");
                    System.out.println("Número da Conta: ");
                    numero = sc.nextInt();                    
                    contas.procurarPorNumero(numero);                    
                    keyPress();
                    break;
                case 4:
                    System.out.println("4- Atualizar Dados da Conta");
                    System.out.println("Número da Agencia: ");
                    numero = sc.nextInt();

                    
                    if (contas.buscarNaCollection(numero) != null) {
                        System.out.println("Número da Agencia: ");
                        agencia = sc.nextInt();
                        
                        System.out.println("Nome do Titular: ");
                        sc.skip("\\R?");
                        titular = sc.nextLine();

                        System.out.println("Saldo da Conta: ");
                        saldo = sc.nextFloat();

                        tipo = contas.retornaTipo(numero);

                    switch (tipo) {
                        case 1 -> {
                            System.out.println("Limite da Conta Corrente: ");
                            limite = sc.nextFloat();
                            contas.atualizar(new ContaCorrente(numero,agencia, tipo, titular, saldo, limite));
                        }
                        case 2 -> {
                            System.out.println("Aniversário da Conta Poupança: ");
                            aniversario = sc.nextInt();
                            contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
                        }
                        default ->{
                            System.out.println("Tipo de Conta inválido!");

                        }
                    }
                    
                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    keyPress();
                    break;
                case 5:
                    System.out.println("5- Apagar Conta");
                    System.out.println("Número da Conta: ");
                    numero = sc.nextInt();
                    contas.deletar(numero);

                    keyPress();
                    break;
                case 6:
                    System.out.println("6- Sacar");
                    
                    System.out.println("Número da Conta: ");
                    numero = sc.nextInt();
                    
                    System.out.println("Valor do Saque: ");
                    valor = sc.nextFloat();
                    
                    contas.depositar(numero, valor);

                    keyPress();
                    break;
                case 7:
                    System.out.println("7- Depositar");
                    System.out.println("Número da Conta: ");
                    numero = sc.nextInt();
                    System.out.println("Valor do depósito: ");
                    valor = sc.nextFloat();
                    
                    contas.depositar(numero, valor);
                    
                    keyPress();
                    break;
                case 8:
                    System.out.println("8- Transferência entre contas\n\n");

                    System.out.println("Número da Conta - Origem: ");
                    numero = sc.nextInt();

                    System.out.println("Número da Conta - Destino: ");
                    numeroDestino = sc.nextInt();

                    System.out.println("Valor da Transferência: ");
                    valor = sc.nextFloat();
                    
                    
                    contas.transferir(numero, numeroDestino, valor);

                    keyPress();
                    break;
                default:
                    System.out.println("Opção inválida!");
                   
            }
        }
    }

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
}
