package fr.araxgaming.deathpenalty;

import java.util.ArrayList;
import java.util.List;

public abstract class LoadableManager extends Loadable {
    private final List<Loadable> loadables = new ArrayList<>();

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        super.load(plugin);
        loadAll();
    }

    @Override
    public void enable(final DeathPenaltyPlugin plugin) {
        super.enable(plugin);
        enableAll();
    }

    @Override
    public void disable(final DeathPenaltyPlugin plugin) {
        super.disable(plugin);
        disableAll();
    }

    protected void registerLoadable(final Loadable loadable) {
        loadables.add(loadable);
    }

    private void loadAll() {
        loadables.forEach((loadable) -> loadable.load(plugin));
    }

    private void enableAll() {
        loadables.forEach((loadable) -> loadable.enable(plugin));
    }

    private void disableAll() {
        loadables.forEach((loadable) -> loadable.disable(plugin));
    }
}
