@Test
    public void testDeposit() {
        double initialBalance = saving.getBalance();
        saving.deposit(200.00);
        assertEquals(initialBalance + 200.00, saving.getBalance(), "Balance should be updated after deposit.");
    }