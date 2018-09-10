/**
 * declares all the constants for the chart color schemes
 *
 * - This is used in batch-details.component, batch-list.component, client-list.component,
 * client-mapped.component, home.component, skillset.components
 * - This declares all the constants for the chart color schemes
 * Note made 6/6/2018
 * Reviewed by Max
 */
import {Color} from 'ng2-charts';

export class ThemeConstants {
  static MAPPED_COLORS: Array<Color> = [{ backgroundColor: ['#ff9c38', '#004672'] }];

  static SKILL_COLORS: Array<Color> = [{ backgroundColor: ['#ff9c38', '#F4B874', '#cc7c2c', '#724614', '#995d21', '#725637', '#663e16','#BF7522'] }];
  static CLIENT_COLORS: Array<Color> = [{ backgroundColor: ['#004672', '#4ba5ff', '#2BA5F2', '#73b9ff', '#2282BF', '#2295BF', '#53CAF4', '#c3e1ff'] }];

  static BATCH_COLORS: Array<Color> = [{ backgroundColor: ['#004672', '#4ba5ff', '#2BA5F2', '#73b9ff', '#2282BF', '#2295BF', '#53CAF4', '#c3e1ff'] }];
  static BAR_COLORS: Array<Color> = [{ backgroundColor: '#e85410'}, { backgroundColor: '#59504c' }];
  static BATCH_DETAILS_COLORS: Array<Color> = [{ backgroundColor: '#ff8d3f' }, { backgroundColor: '#514f4f' }, { backgroundColor: 'black' }];
}
