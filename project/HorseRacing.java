package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HorseRacing {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			List<Horse> hList = new ArrayList<Horse>();
			hList.add(new Horse("1",0.00f));
			hList.add(new Horse("2",0.00f));
			hList.add(new Horse("3",0.00f));
			hList.add(new Horse("4",0.00f));
			hList.add(new Horse("5",0.00f));
			hList.add(new Horse("6",0.00f));
			hList.add(new Horse("7",0.00f));
			hList.add(new Horse("8",0.00f));
			
			System.out.println("1~8번말을 선택해주세요.");
			System.out.println(hList);
			
			String input = scan.next();

			System.out.println();
			System.out.println("경기 시작");

			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Thread p = new Position(hList);
			
			for (Thread th : hList) {
				th.start();
			}
			
			p.start();

			try {
				p.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Collections.sort(hList);
			System.out.println("_____________________");
			System.out.println("경기 결과");
			System.out.println("이번경기 우승은 " + hList.get(0).getHName()+ "번마(승률 : "+hList.get(0).getRate()+")입니다");
					
			for (Horse h : hList) {
				System.out.println(h);
			}

			System.out.println("_____________________");
			System.out.println("당신이 선택한 말은 " + hList.get(0).getHName() + "번마(승률 : "+hList.get(0).getRate()+")입니다.");
			if (input.equals(hList.get(0).getHName())) {
				System.out.println("축하합니다. 선택하신말이 1등으로 들어왔습니다.");
			} else {
				System.out.println("아쉽게도 선택하신 말이 1등으로 들어오지 못했습니다.");
			}
		}
	}
}
