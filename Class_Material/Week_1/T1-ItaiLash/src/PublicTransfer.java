public interface PublicTransfer {

    /**
     *
     * @return how many  passenger are currently in the public Transfer
     */
    int getCurrPassengerCount();

    /**
     * add passenger to the transfer
     */
    void addPassenger();

    /**
     * minus one passenger to the transfer
     */
    void getOffPassenger();

}
