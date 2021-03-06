package zhang.springframework.gof.proxy;

import org.junit.Test;

import zhang.springframework.gof.proxy.ReportGenerator;
import zhang.springframework.gof.proxy.ReportGeneratorImplProxy;
import zhang.springframework.gof.proxy.Role;

import static org.junit.Assert.*;

public class ReportGeneratorImplProxyTest {

    @Test
    public void testGenerateReport() throws Exception {
        Role accessRole=new Role();
        accessRole.setRole("Manager");
        ReportGenerator proxy=new ReportGeneratorImplProxy(accessRole);
        proxy.displayReportTemplate("Pdf",150);
        proxy.generateComplexReport("Pdf",150);
        proxy.generateSensitiveReport();
    }
}