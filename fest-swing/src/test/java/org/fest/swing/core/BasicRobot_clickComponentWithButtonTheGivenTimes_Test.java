/*
 * Created on Jul 25, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.awt.AWT.centerOf;
import static org.fest.swing.core.ClickingDataProvider.clickingData;
import static org.fest.swing.test.recorder.ClickRecorder.attachTo;
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Preconditions.checkNotNull;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.swing.JTextField;

import org.fest.swing.test.recorder.ClickRecorder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for {@link BasicRobot#click(java.awt.Point, MouseButton, int)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class BasicRobot_clickComponentWithButtonTheGivenTimes_Test extends BasicRobot_TestCase {
  private final MouseButton button;
  private final int times;

  @Parameters
  public static @Nonnull Collection<Object[]> buttons() {
    return newArrayList(clickingData());
  }

  public BasicRobot_clickComponentWithButtonTheGivenTimes_Test(@Nonnull MouseButton button, int times) {
    this.button = checkNotNull(button);
    this.times = times;
  }

  @Test
  public void should_click_Component_with_given_mouse_button_and_given_number_of_times() {
    JTextField textField = window().textField();
    ClickRecorder recorder = attachTo(textField);
    robot().click(textField, button, times);
    assertThat(recorder).clicked(button)
                        .timesClicked(times)
                        .clickedAt(centerOf(textField));
  }
}
