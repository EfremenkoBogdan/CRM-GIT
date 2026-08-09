package extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import utils.AllureAttachments;

public class AllureExtensions implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause)  {
        AllureAttachments.attachCurrentUrl();
        AllureAttachments.attachScreenshot();
        AllureAttachments.attachCurrentUrl();
    }

}
