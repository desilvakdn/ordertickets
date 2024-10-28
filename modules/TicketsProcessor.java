package modules;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TicketsProcessor {
    private LinkedList<Ticket> winningTickets = new LinkedList<>();
    private List<Ticket> lostTickets = new ArrayList<>();
    private List<Ticket> orderedTickets = new ArrayList<>();

    public void readTickets(String sourceFilePath){

        System.out.println("Reading file...");

        try(BufferedReader br = new BufferedReader(new FileReader(sourceFilePath))) {
            System.out.println("Processing tickets...");
            String line;
            br.readLine();
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                int barcodeId = Integer.parseInt(row[0]);
                boolean isWinningTicket = Boolean.parseBoolean(row[1]);
                Ticket ticket = new Ticket(barcodeId, isWinningTicket);

                if(isWinningTicket){
                    winningTickets.add(ticket);
                    continue;
                }

                lostTickets.add(ticket);
            }
        } catch (Exception e) {
            System.out.println("Error Occured!");
            e.printStackTrace();
        }
    }

    public void orderTickets(){

        System.out.println("Ordering tickets...");


        if(winningTickets.size()==0){
            this.orderedTickets = this.lostTickets;
        };

        int lostTicketGroupSize = lostTickets.size() / winningTickets.size();
        int pointer = 0;

        for(Ticket lostTicket: lostTickets){
            if(lostTicketGroupSize<=pointer && !winningTickets.isEmpty()){
                this.orderedTickets.add(winningTickets.poll());
                pointer=0;
            }

            this.orderedTickets.add(lostTicket);

            pointer++;
        }

        // If any extra winning tickets available.
        orderedTickets.addAll(winningTickets);

        return;
    }

    public void writeTickets(String outputFilePath){
        System.out.println("Writing ordered tickets...");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            bw.write("Barcode,Winning\n");
            for(Ticket ticket: orderedTickets){
                bw.write(ticket.getRecord());
            }

            System.out.println("Ordered Tickets file has been created successfully!");
        } catch (Exception e) {
            System.out.println("Error Occured While Writing!");
            e.printStackTrace();
            return;
        }
    }
}
