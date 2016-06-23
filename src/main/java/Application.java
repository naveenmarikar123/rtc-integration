import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.ScmRevision;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.command.blame.BlameLine;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.log.DefaultLog;
import org.apache.maven.scm.manager.BasicScmManager;
import org.apache.maven.scm.manager.ScmManager;
import org.apache.maven.scm.provider.ScmProvider;
import org.apache.maven.scm.provider.jazz.JazzScmProvider;
import org.apache.maven.scm.provider.jazz.command.checkout.JazzCheckOutCommand;
import org.apache.maven.scm.provider.jazz.command.checkout.JazzCheckOutConsumer;
import org.apache.maven.scm.provider.jazz.command.consumer.ErrorConsumer;
import org.apache.maven.scm.provider.jazz.repository.JazzScmProviderRepository;
import org.apache.maven.scm.repository.ScmRepository;
import org.codehaus.plexus.util.cli.Commandline;
import java.io.File;

/**
 * Created by naveenmarikar on 22/06/16.
 */
public class Application {

    private static final String JAZZ_SERVER = "scm:jazz:naveenmarikar123;virtusa@7@https://jazz.net/sandbox02-ccm:NavWorkSpace";
    
    

    
    

    public static void main(String[] args) throws ScmException {

        ScmManager scmManager = new BasicScmManager();
        scmManager.setScmProvider("jazz" , new JazzScmProvider());

        ScmRepository scmRepository = scmManager.makeScmRepository(JAZZ_SERVER);

        //Checkout

        CheckOutScmResult result = scmManager.checkOut( scmRepository, new ScmFileSet( new File("TestRTC")));

        //Blame Generator
        BlameScmResult result2 = scmManager.blame(scmRepository,new ScmFileSet(new File("TestRTC/src")),"Main.java");
        for(BlameLine line : result2.getLines()) {
            System.out.println(line.getAuthor()+line.getCommitter()+line.getRevision()+line.getDate());
        }

        //Diff Extractor - Current File with previous revision
        //DiffScmResult result3 = scmManager.diff(scmRepository,new ScmFileSet(new File("TestRTC/src")))

    }
    
    public static String generateJazzServerURL() {
    	
    	StringBuilder url = new StringBuilder();
    	url.append("scm");
    	url.append(":");
    	url.append("jazz");
    	url.append(":");
    	url.append(LoginConstants.username);
    	url.append(";");
    	url.append(LoginConstants.password);
    	url.append("@");
    	url.append(LoginConstants.repositoryUri);
    	url.append(":");
    	url.append(LoginConstants.workspaceName);
    	return url.toString();	
    }

}
