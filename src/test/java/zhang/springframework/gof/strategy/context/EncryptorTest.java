package zhang.springframework.gof.strategy.context;

import zhang.springframework.gof.strategy.context.Encryptor;
import zhang.springframework.gof.strategy.strategies.AesEncryptionStrategy;
import zhang.springframework.gof.strategy.strategies.BlowfishEncryptionStrategy;
import zhang.springframework.gof.strategy.strategies.EncryptionStrategy;

import org.junit.Test;

import static org.junit.Assert.*;


public class EncryptorTest {

    @Test
    public void testEncrypt() throws Exception {
      EncryptionStrategy aesStrategy=new AesEncryptionStrategy();
      Encryptor aesEncryptor=new Encryptor(aesStrategy);
      aesEncryptor.setPlainText("This is plain text");
      aesEncryptor.encrypt();

        EncryptionStrategy blowfishStrategy=new BlowfishEncryptionStrategy();
        Encryptor blowfishEncryptor=new Encryptor(blowfishStrategy);
        blowfishEncryptor.setPlainText("This is plain text");
        blowfishEncryptor.encrypt();
    }
}