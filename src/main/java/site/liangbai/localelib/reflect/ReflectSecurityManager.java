package site.liangbai.localelib.reflect;

public class ReflectSecurityManager extends SecurityManager {
    @Override
    public Class<?>[] getClassContext() {
        return super.getClassContext();
    }
}
