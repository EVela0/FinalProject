# FinalProject


Broke down the logic of what I was trying to achieve then tried to insert into a program. This process makes it easier for me to look at problems in pieces rather then a giant issue. I also prefer this process incase I forget a step so I don't have to rewrite all the code. An immediately am following a blueprint like guideline thats easy for me to follow and know.


Scanner Input
Output: Welcome to the Simple Bank System

Output:
View All Accounts
Deposit Money
Withdraw Money
Create New Account
Save & Exit
Input: Choice_Variable

If:
Choice = 1 →

Output: "--- Account List ---"

If: No accounts exist → “No accounts found.”
Else: Loop through all accounts → display index, name, balance

Else if: Choice = 2 →

If: No accounts exist → “No accounts available.” → loop back to menu
Else:
Output: Display all accounts with indexes
Output: “Enter the index of the account to deposit into:”
Input: Account_Index
Output: “Enter amount to deposit:”
Input: Deposit_Amount
If: Deposit_Amount ≤ 0 → “Amount must be positive.” → loop back
Else: Add Deposit_Amount to account balance at Account_Index
Output: “Deposited $Deposit_Amount into Account_Name’s account.”
If: Account_Index invalid → “That account number doesn’t exist.” → loop back

Else if: Choice = 3 →

If: No accounts exist → “No accounts available.” → loop back to menu
Else:
Output: Display all accounts with indexes
Output: “Enter the index of the account to withdraw from:”
Input: Account_Index
Output: “Enter amount to withdraw:”
Input: Withdraw_Amount
If: Withdraw_Amount ≤ 0 → “Amount must be more than zero.” → loop back
If: Withdraw_Amount > Current_Balance → “Not enough balance in this account.” → loop back
Else: Subtract Withdraw_Amount from account balance at Account_Index
Output: “Withdrew $Withdraw_Amount from Account_Name’s account.”
If: Account_Index invalid → “That account number doesn’t exist.” → loop back

Else if: Choice = 4 →

Output: “Enter account holder name:”
Input: Account_Name
Add Account_Name to account names list
Add 0.0 to account balances list
Output: “New account created for: Account_Name”

Else if: Choice = 5 →

Action: Save all account names to “accounts.txt”
Save all account balances to “balances.txt”
If: Error occurs → “Error saving to [filename]: error message”
Else: “Data saved. Goodbye!”
Exit program

Else: “Invalid choice, try again.” → loop back to menu

Input Validation:

If input must be integer → Try parse integer
If fail: “Not a number, enter again” → retry

If input must be double → Try parse double
If fail: “Invalid amount, enter again” → retry

Load Data at Start:

Try: Load “accounts.txt” → add each line to account names list
If fail: “No account file found, starting new”
Try: Load “balances.txt” → add each line as double to balances list
If fail: “No balances file found, starting empty”
