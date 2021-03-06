package zhang.springframework.gof.visitor.visitors;


import zhang.springframework.gof.visitor.structure.OperaMailClient;
import zhang.springframework.gof.visitor.structure.SquirrelMailClient;
import zhang.springframework.gof.visitor.structure.ZimbraMailClient;

public class MacMailClientVisitor implements MailClientVisitor{
    @Override
    public void visit(OperaMailClient mailClient) {
        System.out.println("Configuration of Opera mail client for Mac complete");
    }

    @Override
    public void visit(SquirrelMailClient mailClient) {
        System.out.println("Configuration of Squirrel mail client for Mac complete");

    }

    @Override
    public void visit(ZimbraMailClient mailClient) {
        System.out.println("Configuration of Zimbra mail client for Mac complete");

    }
}
