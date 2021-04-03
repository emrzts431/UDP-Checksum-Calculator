package UDP_pruefsumme;


import java.util.Arrays;
import java.util.Scanner;

public class pruefsumme {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] header = new String[11];
		//Quelle IP
		System.out.print("erste Byte Quell IP: ");
		int firstByteQuell = scanner.nextInt();
		System.out.print("zweite Byte Quell IP: ");
		int secondByteQuell = scanner.nextInt();
		System.out.print("dritte Byte Quell IP: ");
		int thirdByteQuell = scanner.nextInt();
		System.out.print("vierte Byte Quell IP: ");
		int fourthByteQuell = scanner.nextInt();
		//***********************************
		//Ziel IP
		System.out.print("erste Byte Ziel IP: ");
		int firstByteZiel = scanner.nextInt();
		System.out.print("zweite Byte Ziel IP: ");
		int secondByteZiel = scanner.nextInt();
		System.out.print("dritte Byte Ziel IP: ");
		int thirdByteZiel = scanner.nextInt();
		System.out.print("vierte Byte Ziel IP: ");
		int fourthByteZiel = scanner.nextInt();
		//***********************************
		//protokoll
		System.out.print("Protokoll : ");
		int protokoll = scanner.nextInt();
		//***********************************
		//UDP-Datagramm-Laenge
		System.out.print("UDP-Datagramm-Laenge : ");
		int datagramm_laenge = scanner.nextInt();
		//************************************
		//QuellPort
		System.out.print("Quell Port : ");
		int quellPort = scanner.nextInt();
		//*****************************
		//Ziel Port
		System.out.print("Ziel Port : ");
		int zielPort = scanner.nextInt();
		//******************************
		//Gesamtlaenge
		System.out.print("Gesaemtlaenge : ");
		int gesamlaenge = scanner.nextInt();
		//*****************************************
		//Data
		System.out.print("erste Byte Data : ");
		int firstByteData = scanner.nextInt();
		System.out.print("zweite Byte Data : ");
		int secondByteData = scanner.nextInt();
		System.out.print("dritte Byte Data : ");
		int thirdByteData = scanner.nextInt();
		System.out.print("fourth Byte Data : ");
		int fourthByteData = scanner.nextInt();
		scanner.close();
		String quellIP1 = makeSixteen(toByte(firstByteQuell),toByte(secondByteQuell));
		header[0] = quellIP1;
		String quellIP2 = makeSixteen(toByte(thirdByteQuell),toByte(fourthByteQuell));
		header[1] = quellIP2;
		String zielIP1 = makeSixteen(toByte(firstByteZiel),toByte(secondByteZiel));
		header[2] = zielIP1;
		String zielIP2 = makeSixteen(toByte(thirdByteZiel),toByte(fourthByteZiel));
		header[3] = zielIP2;
		header[4] = makeSixteen(toByte(0),toByte(protokoll));
		header[5] = toHexa(datagramm_laenge);
		header[6] = toHexa(quellPort);
		header[7] = toHexa(zielPort);
		header[8] = toHexa(gesamlaenge);
		String data1 = makeSixteen(toByte(firstByteData),toByte(secondByteData));
		String data2 = makeSixteen(toByte(thirdByteData),toByte(fourthByteData));
		header[9] = data1;
		header[10] = data2;
		System.out.println(Arrays.toString(header));
		System.out.println("*************************************");
		int result = 0;
		for(int i = 0;i<11;i++) {
			result += convertBinaryToDecimal(Long.parseLong(header[i]));
		}
		System.out.println("Erste Summe = "+Integer.toBinaryString(result));
		System.out.println("*******************************************");
		if(Integer.toBinaryString(result).length() == 16) {
			System.out.println("Pruefsumme = "+Integer.toBinaryString(result));
		}
		else if(Integer.toBinaryString(result).length()>16) {
			int end = makeSixteen(result);
			if(Integer.toBinaryString(end).length()<16) {
				System.out.println("Pruefsumme = "+toHexa(end));
			}
			else {
				System.out.println("Pruefsumme = "+Integer.toBinaryString(end));
			}
		}
		else {
			System.out.println("Pruefsumme = "+toHexa(result));
		}
	}
	
	public static String toByte(Integer a) {
		String nmbr = Integer.toBinaryString(a);
		if(nmbr.length()!=8) {
			while(nmbr.length()!=8) {
				nmbr = "0"+nmbr;
			}
		}
		return nmbr;
	}
	public static String toHexa(Integer a) {
		String nmbr = Integer.toBinaryString(a);
		if(nmbr.length()!=8) {
			while(nmbr.length()!=16) {
				nmbr = "0"+nmbr;
			}
		}
		return nmbr;
	}
	
	public static String makeSixteen(String a,String b) {
		return a+b;
	}
	public static Long convertBinaryToDecimal(long binaryNumber) {

	    long decimalNumber = 0;
	    long base = 1;

	    while (binaryNumber > 0) {
	        long lastDigit = binaryNumber % 10;
	        binaryNumber = binaryNumber / 10;
	        decimalNumber += lastDigit * base;
	        base = base * 2;
	    }
	    return decimalNumber;
	}
	
	public static int makeSixteen(int a) {
		if(Integer.toBinaryString(a).length() <= 16) {
			return a;
		}
		else {
			String binary = Integer.toBinaryString(a);
			String sixteen = "";
			String rest = "";
			for(int i = binary.length()-1;i>-1;i--) {
				if (i > binary.length()-17){
					sixteen = binary.charAt(i) + sixteen;
				}
				else {
					rest = binary.charAt(i)+rest;
				}
			}
			System.out.println(sixteen + "+"+rest+" (mit Rest addieren!!)");
			long result = convertBinaryToDecimal(Long.parseLong(sixteen))+convertBinaryToDecimal(Long.parseLong(rest));
			return makeSixteen((int)result);
		}
	}
}
