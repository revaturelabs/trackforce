/**
 * @Author chart style for stronger typing while constructing ng2-charts options/colors
 *
 * As a whole, this model is used in batch-list.component, home.component
 * This file contains the model for SideValues and ChartOptions,
 * as well as the methods createOptionsSpacing, createOptionsTitle, and createOptionsLegend
 * Note made 6/7/2018
 * Reviewed by Max
 */


/**
 * Used to represent padding and margin in chart styles
 *
 */
export class SideValues {
  left: number;
  right: number;
  top: number;
  bottom: number;

  constructor(left: number, right: number, top: number, bottom: number) {
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
  }
}


/**
 * chart options factory for ng2-charts
 */
export class ChartOptions {
  title: { display: boolean, text: string, fontSize: number, fontColor: string };
  legend: { display: boolean, position: string };
  layout: { padding: SideValues, margin: SideValues };
  responsive: boolean;
  maintainAspectRatio: boolean;

  private constructor() {
  }

  /**
   * convenience method for creating chart options with
   * padding and margin options
   *
   * @param {SideValues} padding
   * @param {SideValues} margin
   * @param {string} legendPosition
   * @param {boolean} isResponsive
   * @param {boolean} maintainAspectRatio
   * @returns {ChartOptions}
   */
  public static createOptionsSpacing(padding: SideValues, margin: SideValues,
                                     legendPosition: string,
                                     isResponsive: boolean, maintainAspectRatio: boolean): ChartOptions {

    const options = new ChartOptions();
    options.legend = {display: true, position: legendPosition};
    options.layout = {padding: padding, margin: margin};
    options.responsive = isResponsive;
    options.maintainAspectRatio = maintainAspectRatio;
    return options;
  };

  /**
   * convenience method for construction options with legend and title options
   *
   * @param {string} title
   * @param {number} titleFontSize
   * @param {string} titleColor
   * @param {string} legendPosition
   * @returns {ChartOptions}
   */
  public static createOptionsTitle(title: string, titleFontSize: number, titleColor: string, legendPosition: string): ChartOptions {
    const options = new ChartOptions();
    options.title = {display: true, text: title, fontSize: titleFontSize, fontColor: titleColor};
    options.legend = {display: true, position: legendPosition};
    return options;
  }

  /**
   * convenience method for construction options with legend options
   *
   * @param {string} legendPosition
   * @returns {ChartOptions}
   */
  public static createOptionsLegend(legendPosition: string): ChartOptions {
    const options = new ChartOptions();
    options.legend = {display: true, position: legendPosition};
    return options;
  }
}
