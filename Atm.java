import java.util.*;
import java.lang.*;
class AtmCheck
{  
	Double Balance = 0.0;
	Scanner Scan=new Scanner(System.in);
	public void Account()
	{
	System.out.println("Select the Account you want to access;");
	System.out.println("Type1: Checking Account.");
	System.out.println("Type2: Saving Account.");
	System.out.println("Type3: Exit.");
	System.out.print("Chioce:");
	int Choice =Scan.nextInt();
	while(Choice>0&&Choice<3)
	{
		if(Choice==1)
		{
			Checking_Account();
		}
		else if(Choice==2)
		{
			Saving_Account();
		} 
	}
	if(Choice==3)
	{
		System.out.println("Exit!");
		System.exit(0);
	}
	else
	{
		System.out.println("Invalid Choice");
	}}
	public void Checking_Account()
	{
		System.out.println("Checking Account:");
		System.out.println("Type1: View Balance.");
		System.out.println("Type2: Withdraw Funds.");
		System.out.println("Type3: Deposit Funds.");
		System.out.println("Type4: Exit.");
		System.out.print("Choice:");
		int Choice=Scan.nextInt();
	if(Choice>0&&Choice<5)
	{
		if(Choice==1)
		{
			
			System.out.println("Balance of your Checking Account:"+Balance);
		}
		else if(Choice==2)
		{
			System.out.println("Balance of your Checking Account:"+Balance);
			System.out.print("Amount you want to withdraw:");
			WithdrawFunds();
			System.out.println(" New balance of your Checking Account:"+Balance);
		}
		else if(Choice==3)
		{
		
			System.out.println("Balance of your Checking Account:"+Balance);
			System.out.print("Amount you want to deposit:");
			DepositFunds();
			System.out.println(" New balance of your Checking Account:"+Balance);
	    } 		   
	else if(Choice==4)
	{
		Exit();
	}}
	else{
		System.out.println("invalid Choice");
	}
	}
	public void Saving_Account()
	{
		System.out.println("Saving Account:");
		System.out.println("Type1: View Balance.");
		System.out.println("Type2: Withdraw Funds.");
		System.out.println("Type3: Deposit Funds.");
		System.out.println("Type4: Exit.");
		System.out.print("Choice:");
		int Choice=Scan.nextInt();
		if(Choice>0&&Choice<5)
	    {
		if(Choice==1)
		{
			
			System.out.println("Balance of your Saving Account:"+Balance);
		}
		else if(Choice==2)
		{
			System.out.println("Balance of your Saving Account:"+Balance);
			System.out.print("Amount you want to withdraw:");
			WithdrawFunds();
			System.out.println(" New balance of your Saving Account:"+Balance);
		}
		else if(Choice==3)
		{
		
			System.out.println("Balance of your Saving Account:"+Balance);
			System.out.print("Amount you want to deposit:");
			DepositFunds();
			System.out.println(" New balance of your Saving Account:"+Balance);
	    } 
       else if(Choice==4)
	   {
		   Exit();
		}}		  
	else
	{
		System.out.println("invalid Choice");
	}
	}
	public void WithdrawFunds()
	{
		int Withdraw_Money=Scan.nextInt();
		if(Balance==0||Balance<Withdraw_Money)
		{
			System.out.println("Low Balance! amount cannot be withdrawn");
		}
		else
		{
		Balance=Balance-Withdraw_Money;
		}
	}
	public void DepositFunds()
	{
		int Deposit_Money=Scan.nextInt();
		Balance=Balance+Deposit_Money;
	}
	public void Exit()
	{
		Account();
	}
	
	
	
}
public class Atm
{
	public static void main(String args[])
	{
		long customer_id=8146173336l;
		int pin=3142;
		System.out.println("WELCOME TO THE ATM PROJECT");
                System.out.println("READY TO ACCESS THE FEATURES");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Customer number:");
		long Customerid = scan.nextLong();
		System.out.println("Enter the Pin number");
		int pin_number=scan.nextInt();
		if(Customerid!=customer_id||pin_number!=pin||Customerid<0||pin_number<0)
		{
			System.out.println("Invalid id or pin!");
		System.exit(0);}
		AtmCheck ab=new AtmCheck();
	    ab.Account();
		}
}