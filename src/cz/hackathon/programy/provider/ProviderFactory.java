package cz.hackathon.programy.provider;

/**
 * User: lukas.marek@cleverlance.com
 * Date: 22.5.11
 * Time: 12:27
 */
public class ProviderFactory {
    public static ActionProvider getProvider() {
        return new FakeProvider();
    }
}
