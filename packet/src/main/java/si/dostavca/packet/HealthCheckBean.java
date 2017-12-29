package si.dostavca.packet;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@Health
@ApplicationScoped
public class HealthCheckBean implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named(HealthCheckBean.class.getSimpleName()).up().build();
    }
}
