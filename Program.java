import modules.TicketsProcessor;

public class Program {
  public static void main(String[] args) {
    TicketsProcessor ticketProcessor = new TicketsProcessor();
    ticketProcessor.readTickets("./data/lottery_tickets.csv");
    ticketProcessor.orderTickets();
    ticketProcessor.writeTickets("./data/ordered_lottery_tickets.csv");
  }
}