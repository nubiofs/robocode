/*******************************************************************************
 * Copyright (c) 2001, 2008 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/cpl-v10.html
 *
 * Contributors:
 *     Pavel Savara
 *     - Initial implementation
 *******************************************************************************/

package robots;


import helpers.Assert;
import helpers.RobotTestBed;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import robocode.battle.events.BattleStartedEvent;
import robocode.battle.events.TurnEndedEvent;
import robocode.battle.snapshot.RobotSnapshot;


/**
 * Repeatable robot possition test
 *
 * @author Pavel Savara (original)
 */
public class TestPosition extends RobotTestBed {
	int lastTurn;

	@Test
	public void run() {
		super.run();
	}

	public String getRobotNames() {
		return "sample.Crazy,sample.Target";
	}

	@Override
	public void onBattleStarted(BattleStartedEvent event) {
		super.onBattleStarted(event);
        
		RobotSnapshot crazy = event.getTurnSnapshot().getRobots().get(0);
		RobotSnapshot target = event.getTurnSnapshot().getRobots().get(1);

		Assert.assertNear(566.2968069, crazy.getX());
		Assert.assertNear(165.0789361, crazy.getY());
		Assert.assertNear(436.3146436, target.getX());
		Assert.assertNear(350.7235444, target.getY());
	}

	@Override
	public void onTurnEnded(TurnEndedEvent event) {
		super.onTurnEnded(event);
		lastTurn = event.getTurnSnapshot().getTurn();

		// System.out.println(event.getTurnSnapshot().getTurn());
		RobotSnapshot crazy = event.getTurnSnapshot().getRobots().get(0);
		RobotSnapshot target = event.getTurnSnapshot().getRobots().get(1);

		if (lastTurn == 1) {
			Assert.assertNear(565.4354411, crazy.getX());
			Assert.assertNear(164.5709508, crazy.getY());
			Assert.assertNear(436.3146436, target.getX());
			Assert.assertNear(350.7235444, target.getY());
		}

		if (lastTurn == 2000) {
			Assert.assertNear(301.3823965, crazy.getX());
			Assert.assertNear(316.1103866, crazy.getY());
			Assert.assertNear(370.7450460, target.getX());
			Assert.assertNear(492.5622427, target.getY());
		}
	}

	@Override
	protected void runTeardown() {
		Assert.assertThat(lastTurn, is(2076));
	}
}
