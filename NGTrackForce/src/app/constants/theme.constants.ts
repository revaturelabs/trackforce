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
  static MAPPED_COLORS: Array<Color> = [{ backgroundColor: ['#ff8d3f', '#514f4f'] }];
  static CLIENT_COLORS: Array<Color> = [{ backgroundColor: ['#68a225', '#506d2f', '#324851', '#b3de81', '#7da3a1', '#a2c523', '#6e6702', '#2e4600', 
                                                          '1D2D0B', '161E0D', '#0E1417', '#313D24', '#232D2C', '#87A51C', '#569E88', '#DDE9AF', 
                                                          '#CAC7A3', '#B3BBA2','#D3DEAC', '#C1DBD3'] }];
  static SKILL_COLORS: Array<Color> = [{ backgroundColor: ['#004d47', '#00cffa', '#52958b', '#008dcb', '#b2dbd5', '#6eb5c0', '#006c84', '#113743'] }];
  static BATCH_COLORS: Array<Color> = [{ backgroundColor: ['#004d47', '#00cffa', '#52958b', '#008dcb', '#b2dbd5', '#6eb5c0', '#006c84', '#113743'] }];
  static BAR_COLORS: Array<Color> = [{ backgroundColor: '#e85410'}, { backgroundColor: '#59504c' }];
  static BATCH_DETAILS_COLORS: Array<Color> = [{ backgroundColor: '#ff8d3f' }, { backgroundColor: '#514f4f' }, { backgroundColor: 'black' }];
}
