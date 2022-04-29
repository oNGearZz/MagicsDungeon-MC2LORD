package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Tab;

public class Option {
    private boolean sendCreationMessage;

    public Option sendCreationMessage(boolean sendCreationMessage) {
        this.sendCreationMessage = sendCreationMessage;
        return this;
    }

    public boolean sendCreationMessage() {
        return this.sendCreationMessage;
    }

    public static Option getDefaultOptions() {
        return (new Option()).sendCreationMessage(true);
    }
}
