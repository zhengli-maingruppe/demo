package mtj.level2;

import java.util.Vector;

public class ProcStation extends Station implements EventListener {
	private Vector<EventSender> eventSenders = new Vector<EventSender>();

	public ProcStation(String name) {
		super(name);
	}

	@Override
	public void setSender(EventSender sender) {
		eventSenders.add(sender);
		System.out.println(this.getName() + " set '" + ((Station) sender).getName() + "' as sender");
	}

	@Override
	public void detachSender(EventSender sender) {
		eventSenders.remove(sender);
		System.out.println(this.getName() + " has removed the sender '" + ((Station) sender).getName() + "'");
	}

	@Override
	public void releaseFromSenders() {
		for (EventSender sender : eventSenders) {
			sender.detachListener(this);
			eventSenders.remove(sender);

		}
	}

	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub

	}

}
