/**
 * - This is used in the skillset.component
 * - The purpose of this model is to abstract the creation of the y-axis on the bar chart
 * Note made 6/6/2018
 * @author Amelia
 */
export class ChartScale {
  yAxes: { [k: string]: any }[] = [{
    ticks: {
      min: 0
    }
  }
  ];

  constructor() {
  }
}
