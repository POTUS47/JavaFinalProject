/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
$(document).ready(function() {

    $(".click-title").mouseenter( function(    e){
        e.preventDefault();
        this.style.cursor="pointer";
    });
    $(".click-title").mousedown( function(event){
        event.preventDefault();
    });

    // Ugly code while this script is shared among several pages
    try{
        refreshHitsPerSecond(true);
    } catch(e){}
    try{
        refreshResponseTimeOverTime(true);
    } catch(e){}
    try{
        refreshResponseTimePercentiles();
    } catch(e){}
});


var responseTimePercentilesInfos = {
        data: {"result": {"minY": 92.0, "minX": 0.0, "maxY": 2108.0, "series": [{"data": [[0.0, 92.0], [0.1, 117.0], [0.2, 125.0], [0.3, 129.0], [0.4, 132.0], [0.5, 134.0], [0.6, 135.0], [0.7, 137.0], [0.8, 139.0], [0.9, 140.0], [1.0, 142.0], [1.1, 143.0], [1.2, 144.0], [1.3, 146.0], [1.4, 148.0], [1.5, 149.0], [1.6, 150.0], [1.7, 151.0], [1.8, 153.0], [1.9, 154.0], [2.0, 155.0], [2.1, 157.0], [2.2, 158.0], [2.3, 160.0], [2.4, 161.0], [2.5, 163.0], [2.6, 164.0], [2.7, 165.0], [2.8, 167.0], [2.9, 170.0], [3.0, 171.0], [3.1, 173.0], [3.2, 174.0], [3.3, 177.0], [3.4, 179.0], [3.5, 181.0], [3.6, 183.0], [3.7, 186.0], [3.8, 189.0], [3.9, 191.0], [4.0, 193.0], [4.1, 196.0], [4.2, 199.0], [4.3, 201.0], [4.4, 204.0], [4.5, 207.0], [4.6, 211.0], [4.7, 215.0], [4.8, 219.0], [4.9, 225.0], [5.0, 231.0], [5.1, 236.0], [5.2, 243.0], [5.3, 253.0], [5.4, 265.0], [5.5, 283.0], [5.6, 306.0], [5.7, 334.0], [5.8, 367.0], [5.9, 406.0], [6.0, 521.0], [6.1, 533.0], [6.2, 539.0], [6.3, 543.0], [6.4, 546.0], [6.5, 548.0], [6.6, 550.0], [6.7, 551.0], [6.8, 553.0], [6.9, 555.0], [7.0, 557.0], [7.1, 558.0], [7.2, 559.0], [7.3, 561.0], [7.4, 562.0], [7.5, 563.0], [7.6, 564.0], [7.7, 565.0], [7.8, 566.0], [7.9, 567.0], [8.0, 567.0], [8.1, 568.0], [8.2, 569.0], [8.3, 569.0], [8.4, 570.0], [8.5, 571.0], [8.6, 572.0], [8.7, 572.0], [8.8, 573.0], [8.9, 573.0], [9.0, 574.0], [9.1, 574.0], [9.2, 575.0], [9.3, 576.0], [9.4, 576.0], [9.5, 576.0], [9.6, 577.0], [9.7, 577.0], [9.8, 578.0], [9.9, 579.0], [10.0, 579.0], [10.1, 580.0], [10.2, 580.0], [10.3, 581.0], [10.4, 581.0], [10.5, 582.0], [10.6, 582.0], [10.7, 583.0], [10.8, 583.0], [10.9, 583.0], [11.0, 584.0], [11.1, 584.0], [11.2, 584.0], [11.3, 585.0], [11.4, 585.0], [11.5, 586.0], [11.6, 586.0], [11.7, 587.0], [11.8, 587.0], [11.9, 587.0], [12.0, 587.0], [12.1, 588.0], [12.2, 588.0], [12.3, 589.0], [12.4, 589.0], [12.5, 589.0], [12.6, 589.0], [12.7, 590.0], [12.8, 590.0], [12.9, 590.0], [13.0, 591.0], [13.1, 591.0], [13.2, 591.0], [13.3, 592.0], [13.4, 592.0], [13.5, 592.0], [13.6, 593.0], [13.7, 593.0], [13.8, 593.0], [13.9, 593.0], [14.0, 594.0], [14.1, 594.0], [14.2, 594.0], [14.3, 594.0], [14.4, 595.0], [14.5, 595.0], [14.6, 595.0], [14.7, 596.0], [14.8, 596.0], [14.9, 596.0], [15.0, 597.0], [15.1, 597.0], [15.2, 597.0], [15.3, 598.0], [15.4, 598.0], [15.5, 598.0], [15.6, 598.0], [15.7, 599.0], [15.8, 599.0], [15.9, 599.0], [16.0, 599.0], [16.1, 600.0], [16.2, 600.0], [16.3, 600.0], [16.4, 600.0], [16.5, 601.0], [16.6, 601.0], [16.7, 601.0], [16.8, 601.0], [16.9, 601.0], [17.0, 602.0], [17.1, 602.0], [17.2, 602.0], [17.3, 602.0], [17.4, 603.0], [17.5, 603.0], [17.6, 603.0], [17.7, 603.0], [17.8, 603.0], [17.9, 604.0], [18.0, 604.0], [18.1, 604.0], [18.2, 604.0], [18.3, 604.0], [18.4, 605.0], [18.5, 605.0], [18.6, 605.0], [18.7, 605.0], [18.8, 605.0], [18.9, 606.0], [19.0, 606.0], [19.1, 606.0], [19.2, 606.0], [19.3, 606.0], [19.4, 607.0], [19.5, 607.0], [19.6, 607.0], [19.7, 607.0], [19.8, 607.0], [19.9, 607.0], [20.0, 608.0], [20.1, 608.0], [20.2, 608.0], [20.3, 608.0], [20.4, 608.0], [20.5, 609.0], [20.6, 609.0], [20.7, 609.0], [20.8, 609.0], [20.9, 609.0], [21.0, 609.0], [21.1, 610.0], [21.2, 610.0], [21.3, 610.0], [21.4, 610.0], [21.5, 610.0], [21.6, 611.0], [21.7, 611.0], [21.8, 611.0], [21.9, 611.0], [22.0, 611.0], [22.1, 612.0], [22.2, 612.0], [22.3, 612.0], [22.4, 612.0], [22.5, 612.0], [22.6, 613.0], [22.7, 613.0], [22.8, 613.0], [22.9, 613.0], [23.0, 613.0], [23.1, 614.0], [23.2, 614.0], [23.3, 614.0], [23.4, 614.0], [23.5, 614.0], [23.6, 614.0], [23.7, 615.0], [23.8, 615.0], [23.9, 615.0], [24.0, 615.0], [24.1, 615.0], [24.2, 615.0], [24.3, 616.0], [24.4, 616.0], [24.5, 616.0], [24.6, 616.0], [24.7, 617.0], [24.8, 617.0], [24.9, 617.0], [25.0, 617.0], [25.1, 617.0], [25.2, 617.0], [25.3, 618.0], [25.4, 618.0], [25.5, 618.0], [25.6, 618.0], [25.7, 618.0], [25.8, 619.0], [25.9, 619.0], [26.0, 619.0], [26.1, 619.0], [26.2, 619.0], [26.3, 619.0], [26.4, 620.0], [26.5, 620.0], [26.6, 620.0], [26.7, 620.0], [26.8, 620.0], [26.9, 621.0], [27.0, 621.0], [27.1, 621.0], [27.2, 621.0], [27.3, 621.0], [27.4, 622.0], [27.5, 622.0], [27.6, 622.0], [27.7, 622.0], [27.8, 622.0], [27.9, 622.0], [28.0, 623.0], [28.1, 623.0], [28.2, 623.0], [28.3, 623.0], [28.4, 623.0], [28.5, 623.0], [28.6, 624.0], [28.7, 624.0], [28.8, 624.0], [28.9, 624.0], [29.0, 624.0], [29.1, 625.0], [29.2, 625.0], [29.3, 625.0], [29.4, 625.0], [29.5, 625.0], [29.6, 626.0], [29.7, 626.0], [29.8, 626.0], [29.9, 626.0], [30.0, 626.0], [30.1, 626.0], [30.2, 627.0], [30.3, 627.0], [30.4, 627.0], [30.5, 627.0], [30.6, 627.0], [30.7, 627.0], [30.8, 628.0], [30.9, 628.0], [31.0, 628.0], [31.1, 628.0], [31.2, 628.0], [31.3, 628.0], [31.4, 629.0], [31.5, 629.0], [31.6, 629.0], [31.7, 629.0], [31.8, 629.0], [31.9, 629.0], [32.0, 630.0], [32.1, 630.0], [32.2, 630.0], [32.3, 630.0], [32.4, 630.0], [32.5, 630.0], [32.6, 631.0], [32.7, 631.0], [32.8, 631.0], [32.9, 631.0], [33.0, 631.0], [33.1, 631.0], [33.2, 632.0], [33.3, 632.0], [33.4, 632.0], [33.5, 632.0], [33.6, 632.0], [33.7, 632.0], [33.8, 633.0], [33.9, 633.0], [34.0, 633.0], [34.1, 633.0], [34.2, 633.0], [34.3, 633.0], [34.4, 634.0], [34.5, 634.0], [34.6, 634.0], [34.7, 634.0], [34.8, 634.0], [34.9, 634.0], [35.0, 634.0], [35.1, 635.0], [35.2, 635.0], [35.3, 635.0], [35.4, 635.0], [35.5, 635.0], [35.6, 636.0], [35.7, 636.0], [35.8, 636.0], [35.9, 636.0], [36.0, 636.0], [36.1, 636.0], [36.2, 636.0], [36.3, 637.0], [36.4, 637.0], [36.5, 637.0], [36.6, 637.0], [36.7, 637.0], [36.8, 638.0], [36.9, 638.0], [37.0, 638.0], [37.1, 638.0], [37.2, 638.0], [37.3, 638.0], [37.4, 639.0], [37.5, 639.0], [37.6, 639.0], [37.7, 639.0], [37.8, 639.0], [37.9, 640.0], [38.0, 640.0], [38.1, 640.0], [38.2, 640.0], [38.3, 640.0], [38.4, 640.0], [38.5, 641.0], [38.6, 641.0], [38.7, 641.0], [38.8, 641.0], [38.9, 641.0], [39.0, 642.0], [39.1, 642.0], [39.2, 642.0], [39.3, 642.0], [39.4, 643.0], [39.5, 643.0], [39.6, 643.0], [39.7, 643.0], [39.8, 643.0], [39.9, 643.0], [40.0, 644.0], [40.1, 644.0], [40.2, 644.0], [40.3, 644.0], [40.4, 644.0], [40.5, 645.0], [40.6, 645.0], [40.7, 645.0], [40.8, 645.0], [40.9, 645.0], [41.0, 645.0], [41.1, 645.0], [41.2, 646.0], [41.3, 646.0], [41.4, 646.0], [41.5, 646.0], [41.6, 646.0], [41.7, 647.0], [41.8, 647.0], [41.9, 647.0], [42.0, 647.0], [42.1, 647.0], [42.2, 647.0], [42.3, 648.0], [42.4, 648.0], [42.5, 648.0], [42.6, 648.0], [42.7, 648.0], [42.8, 648.0], [42.9, 648.0], [43.0, 649.0], [43.1, 649.0], [43.2, 649.0], [43.3, 649.0], [43.4, 649.0], [43.5, 649.0], [43.6, 650.0], [43.7, 650.0], [43.8, 650.0], [43.9, 650.0], [44.0, 650.0], [44.1, 651.0], [44.2, 651.0], [44.3, 651.0], [44.4, 651.0], [44.5, 651.0], [44.6, 651.0], [44.7, 652.0], [44.8, 652.0], [44.9, 652.0], [45.0, 652.0], [45.1, 652.0], [45.2, 652.0], [45.3, 653.0], [45.4, 653.0], [45.5, 653.0], [45.6, 653.0], [45.7, 653.0], [45.8, 654.0], [45.9, 654.0], [46.0, 654.0], [46.1, 654.0], [46.2, 654.0], [46.3, 654.0], [46.4, 655.0], [46.5, 655.0], [46.6, 655.0], [46.7, 655.0], [46.8, 655.0], [46.9, 655.0], [47.0, 656.0], [47.1, 656.0], [47.2, 656.0], [47.3, 656.0], [47.4, 656.0], [47.5, 657.0], [47.6, 657.0], [47.7, 657.0], [47.8, 657.0], [47.9, 657.0], [48.0, 657.0], [48.1, 658.0], [48.2, 658.0], [48.3, 658.0], [48.4, 658.0], [48.5, 658.0], [48.6, 659.0], [48.7, 659.0], [48.8, 659.0], [48.9, 659.0], [49.0, 659.0], [49.1, 659.0], [49.2, 660.0], [49.3, 660.0], [49.4, 660.0], [49.5, 660.0], [49.6, 660.0], [49.7, 660.0], [49.8, 661.0], [49.9, 661.0], [50.0, 661.0], [50.1, 661.0], [50.2, 661.0], [50.3, 662.0], [50.4, 662.0], [50.5, 662.0], [50.6, 662.0], [50.7, 662.0], [50.8, 662.0], [50.9, 663.0], [51.0, 663.0], [51.1, 663.0], [51.2, 663.0], [51.3, 663.0], [51.4, 664.0], [51.5, 664.0], [51.6, 664.0], [51.7, 664.0], [51.8, 664.0], [51.9, 665.0], [52.0, 665.0], [52.1, 665.0], [52.2, 665.0], [52.3, 665.0], [52.4, 666.0], [52.5, 666.0], [52.6, 666.0], [52.7, 666.0], [52.8, 666.0], [52.9, 667.0], [53.0, 667.0], [53.1, 667.0], [53.2, 667.0], [53.3, 667.0], [53.4, 667.0], [53.5, 668.0], [53.6, 668.0], [53.7, 668.0], [53.8, 668.0], [53.9, 668.0], [54.0, 669.0], [54.1, 669.0], [54.2, 669.0], [54.3, 669.0], [54.4, 670.0], [54.5, 670.0], [54.6, 670.0], [54.7, 670.0], [54.8, 670.0], [54.9, 671.0], [55.0, 671.0], [55.1, 671.0], [55.2, 671.0], [55.3, 671.0], [55.4, 671.0], [55.5, 672.0], [55.6, 672.0], [55.7, 672.0], [55.8, 672.0], [55.9, 672.0], [56.0, 673.0], [56.1, 673.0], [56.2, 673.0], [56.3, 673.0], [56.4, 673.0], [56.5, 674.0], [56.6, 674.0], [56.7, 674.0], [56.8, 674.0], [56.9, 674.0], [57.0, 675.0], [57.1, 675.0], [57.2, 675.0], [57.3, 675.0], [57.4, 675.0], [57.5, 676.0], [57.6, 676.0], [57.7, 676.0], [57.8, 676.0], [57.9, 677.0], [58.0, 677.0], [58.1, 677.0], [58.2, 677.0], [58.3, 677.0], [58.4, 678.0], [58.5, 678.0], [58.6, 678.0], [58.7, 678.0], [58.8, 678.0], [58.9, 679.0], [59.0, 679.0], [59.1, 679.0], [59.2, 679.0], [59.3, 680.0], [59.4, 680.0], [59.5, 680.0], [59.6, 680.0], [59.7, 680.0], [59.8, 681.0], [59.9, 681.0], [60.0, 681.0], [60.1, 681.0], [60.2, 681.0], [60.3, 682.0], [60.4, 682.0], [60.5, 682.0], [60.6, 682.0], [60.7, 683.0], [60.8, 683.0], [60.9, 683.0], [61.0, 683.0], [61.1, 684.0], [61.2, 684.0], [61.3, 684.0], [61.4, 684.0], [61.5, 685.0], [61.6, 685.0], [61.7, 685.0], [61.8, 685.0], [61.9, 685.0], [62.0, 686.0], [62.1, 686.0], [62.2, 686.0], [62.3, 686.0], [62.4, 687.0], [62.5, 687.0], [62.6, 687.0], [62.7, 688.0], [62.8, 688.0], [62.9, 688.0], [63.0, 688.0], [63.1, 688.0], [63.2, 688.0], [63.3, 689.0], [63.4, 689.0], [63.5, 689.0], [63.6, 689.0], [63.7, 690.0], [63.8, 690.0], [63.9, 690.0], [64.0, 690.0], [64.1, 691.0], [64.2, 691.0], [64.3, 691.0], [64.4, 691.0], [64.5, 692.0], [64.6, 692.0], [64.7, 692.0], [64.8, 692.0], [64.9, 693.0], [65.0, 693.0], [65.1, 693.0], [65.2, 693.0], [65.3, 694.0], [65.4, 694.0], [65.5, 694.0], [65.6, 694.0], [65.7, 695.0], [65.8, 695.0], [65.9, 695.0], [66.0, 695.0], [66.1, 696.0], [66.2, 696.0], [66.3, 696.0], [66.4, 696.0], [66.5, 697.0], [66.6, 697.0], [66.7, 697.0], [66.8, 698.0], [66.9, 698.0], [67.0, 698.0], [67.1, 699.0], [67.2, 699.0], [67.3, 699.0], [67.4, 700.0], [67.5, 700.0], [67.6, 700.0], [67.7, 700.0], [67.8, 701.0], [67.9, 701.0], [68.0, 701.0], [68.1, 701.0], [68.2, 702.0], [68.3, 702.0], [68.4, 702.0], [68.5, 703.0], [68.6, 703.0], [68.7, 703.0], [68.8, 703.0], [68.9, 704.0], [69.0, 704.0], [69.1, 704.0], [69.2, 704.0], [69.3, 705.0], [69.4, 705.0], [69.5, 705.0], [69.6, 706.0], [69.7, 706.0], [69.8, 706.0], [69.9, 706.0], [70.0, 707.0], [70.1, 707.0], [70.2, 707.0], [70.3, 707.0], [70.4, 708.0], [70.5, 708.0], [70.6, 708.0], [70.7, 708.0], [70.8, 709.0], [70.9, 709.0], [71.0, 709.0], [71.1, 710.0], [71.2, 710.0], [71.3, 711.0], [71.4, 711.0], [71.5, 711.0], [71.6, 711.0], [71.7, 712.0], [71.8, 712.0], [71.9, 712.0], [72.0, 713.0], [72.1, 713.0], [72.2, 714.0], [72.3, 714.0], [72.4, 714.0], [72.5, 715.0], [72.6, 715.0], [72.7, 715.0], [72.8, 716.0], [72.9, 716.0], [73.0, 717.0], [73.1, 717.0], [73.2, 717.0], [73.3, 717.0], [73.4, 718.0], [73.5, 718.0], [73.6, 719.0], [73.7, 719.0], [73.8, 720.0], [73.9, 720.0], [74.0, 720.0], [74.1, 721.0], [74.2, 721.0], [74.3, 721.0], [74.4, 722.0], [74.5, 722.0], [74.6, 722.0], [74.7, 723.0], [74.8, 723.0], [74.9, 723.0], [75.0, 724.0], [75.1, 724.0], [75.2, 724.0], [75.3, 725.0], [75.4, 725.0], [75.5, 726.0], [75.6, 726.0], [75.7, 726.0], [75.8, 727.0], [75.9, 727.0], [76.0, 727.0], [76.1, 728.0], [76.2, 728.0], [76.3, 729.0], [76.4, 729.0], [76.5, 730.0], [76.6, 730.0], [76.7, 731.0], [76.8, 731.0], [76.9, 732.0], [77.0, 732.0], [77.1, 733.0], [77.2, 733.0], [77.3, 734.0], [77.4, 734.0], [77.5, 734.0], [77.6, 735.0], [77.7, 735.0], [77.8, 736.0], [77.9, 736.0], [78.0, 737.0], [78.1, 737.0], [78.2, 738.0], [78.3, 738.0], [78.4, 739.0], [78.5, 739.0], [78.6, 740.0], [78.7, 740.0], [78.8, 741.0], [78.9, 742.0], [79.0, 742.0], [79.1, 743.0], [79.2, 743.0], [79.3, 744.0], [79.4, 744.0], [79.5, 745.0], [79.6, 745.0], [79.7, 745.0], [79.8, 746.0], [79.9, 746.0], [80.0, 747.0], [80.1, 747.0], [80.2, 748.0], [80.3, 748.0], [80.4, 749.0], [80.5, 749.0], [80.6, 750.0], [80.7, 750.0], [80.8, 751.0], [80.9, 751.0], [81.0, 752.0], [81.1, 753.0], [81.2, 753.0], [81.3, 754.0], [81.4, 754.0], [81.5, 755.0], [81.6, 755.0], [81.7, 756.0], [81.8, 756.0], [81.9, 757.0], [82.0, 757.0], [82.1, 758.0], [82.2, 759.0], [82.3, 759.0], [82.4, 760.0], [82.5, 760.0], [82.6, 761.0], [82.7, 762.0], [82.8, 763.0], [82.9, 764.0], [83.0, 764.0], [83.1, 765.0], [83.2, 765.0], [83.3, 766.0], [83.4, 766.0], [83.5, 767.0], [83.6, 767.0], [83.7, 768.0], [83.8, 769.0], [83.9, 769.0], [84.0, 770.0], [84.1, 770.0], [84.2, 771.0], [84.3, 772.0], [84.4, 772.0], [84.5, 773.0], [84.6, 774.0], [84.7, 774.0], [84.8, 775.0], [84.9, 776.0], [85.0, 777.0], [85.1, 778.0], [85.2, 778.0], [85.3, 778.0], [85.4, 779.0], [85.5, 780.0], [85.6, 781.0], [85.7, 782.0], [85.8, 782.0], [85.9, 783.0], [86.0, 784.0], [86.1, 785.0], [86.2, 785.0], [86.3, 786.0], [86.4, 787.0], [86.5, 788.0], [86.6, 789.0], [86.7, 789.0], [86.8, 790.0], [86.9, 791.0], [87.0, 792.0], [87.1, 794.0], [87.2, 794.0], [87.3, 796.0], [87.4, 797.0], [87.5, 798.0], [87.6, 799.0], [87.7, 799.0], [87.8, 800.0], [87.9, 801.0], [88.0, 803.0], [88.1, 804.0], [88.2, 805.0], [88.3, 806.0], [88.4, 806.0], [88.5, 807.0], [88.6, 808.0], [88.7, 809.0], [88.8, 810.0], [88.9, 812.0], [89.0, 813.0], [89.1, 814.0], [89.2, 815.0], [89.3, 816.0], [89.4, 818.0], [89.5, 819.0], [89.6, 821.0], [89.7, 822.0], [89.8, 823.0], [89.9, 825.0], [90.0, 826.0], [90.1, 827.0], [90.2, 829.0], [90.3, 831.0], [90.4, 832.0], [90.5, 834.0], [90.6, 835.0], [90.7, 837.0], [90.8, 839.0], [90.9, 841.0], [91.0, 843.0], [91.1, 845.0], [91.2, 847.0], [91.3, 848.0], [91.4, 851.0], [91.5, 853.0], [91.6, 855.0], [91.7, 857.0], [91.8, 859.0], [91.9, 861.0], [92.0, 863.0], [92.1, 865.0], [92.2, 868.0], [92.3, 870.0], [92.4, 873.0], [92.5, 877.0], [92.6, 880.0], [92.7, 883.0], [92.8, 886.0], [92.9, 888.0], [93.0, 891.0], [93.1, 894.0], [93.2, 898.0], [93.3, 901.0], [93.4, 905.0], [93.5, 910.0], [93.6, 911.0], [93.7, 917.0], [93.8, 922.0], [93.9, 929.0], [94.0, 935.0], [94.1, 941.0], [94.2, 947.0], [94.3, 954.0], [94.4, 964.0], [94.5, 975.0], [94.6, 991.0], [94.7, 1004.0], [94.8, 1019.0], [94.9, 1032.0], [95.0, 1041.0], [95.1, 1050.0], [95.2, 1061.0], [95.3, 1071.0], [95.4, 1077.0], [95.5, 1082.0], [95.6, 1087.0], [95.7, 1093.0], [95.8, 1097.0], [95.9, 1106.0], [96.0, 1111.0], [96.1, 1116.0], [96.2, 1123.0], [96.3, 1130.0], [96.4, 1133.0], [96.5, 1140.0], [96.6, 1146.0], [96.7, 1152.0], [96.8, 1156.0], [96.9, 1161.0], [97.0, 1169.0], [97.1, 1174.0], [97.2, 1180.0], [97.3, 1185.0], [97.4, 1188.0], [97.5, 1194.0], [97.6, 1199.0], [97.7, 1204.0], [97.8, 1210.0], [97.9, 1217.0], [98.0, 1223.0], [98.1, 1232.0], [98.2, 1241.0], [98.3, 1252.0], [98.4, 1260.0], [98.5, 1270.0], [98.6, 1284.0], [98.7, 1295.0], [98.8, 1305.0], [98.9, 1318.0], [99.0, 1337.0], [99.1, 1355.0], [99.2, 1376.0], [99.3, 1393.0], [99.4, 1423.0], [99.5, 1458.0], [99.6, 1498.0], [99.7, 1561.0], [99.8, 1637.0], [99.9, 1743.0], [100.0, 2108.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
        getOptions: function() {
            return {
                series: {
                    points: { show: false }
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentiles'
                },
                xaxis: {
                    tickDecimals: 1,
                    axisLabel: "Percentiles",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Percentile value in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : %x.2 percentile was %y ms"
                },
                selection: { mode: "xy" },
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentiles"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesPercentiles"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesPercentiles"), dataset, prepareOverviewOptions(options));
        }
};

/**
 * @param elementId Id of element where we display message
 */
function setEmptyGraph(elementId) {
    $(function() {
        $(elementId).text("No graph series with filter="+seriesFilter);
    });
}

// Response times percentiles
function refreshResponseTimePercentiles() {
    var infos = responseTimePercentilesInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimePercentiles");
        return;
    }
    if (isGraph($("#flotResponseTimesPercentiles"))){
        infos.createGraph();
    } else {
        var choiceContainer = $("#choicesResponseTimePercentiles");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesPercentiles", "#overviewResponseTimesPercentiles");
        $('#bodyResponseTimePercentiles .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimeDistributionInfos = {
        data: {"result": {"minY": 1.0, "minX": 0.0, "maxY": 9017.0, "series": [{"data": [[0.0, 2.0], [2100.0, 1.0], [600.0, 9017.0], [700.0, 3577.0], [200.0, 232.0], [800.0, 966.0], [900.0, 252.0], [1000.0, 205.0], [1100.0, 314.0], [300.0, 55.0], [1200.0, 201.0], [1300.0, 99.0], [1400.0, 50.0], [1500.0, 24.0], [100.0, 745.0], [400.0, 16.0], [1600.0, 21.0], [1700.0, 15.0], [1800.0, 4.0], [1900.0, 4.0], [500.0, 1780.0], [2000.0, 1.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 2100.0, "title": "Response Time Distribution"}},
        getOptions: function() {
            var granularity = this.data.result.granularity;
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    barWidth: this.data.result.granularity
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " responses for " + label + " were between " + xval + " and " + (xval + granularity) + " ms";
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimeDistribution"), prepareData(data.result.series, $("#choicesResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshResponseTimeDistribution() {
    var infos = responseTimeDistributionInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeDistribution");
        return;
    }
    if (isGraph($("#flotResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var syntheticResponseTimeDistributionInfos = {
        data: {"result": {"minY": 69.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 16462.0, "series": [{"data": [[0.0, 1050.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 16462.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [[2.0, 69.0]], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 2.0, "title": "Synthetic Response Times Distribution"}},
        getOptions: function() {
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendSyntheticResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times ranges",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                    tickLength:0,
                    min:-0.5,
                    max:3.5
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    align: "center",
                    barWidth: 0.25,
                    fill:.75
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " " + label;
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            options.xaxis.ticks = data.result.ticks;
            $.plot($("#flotSyntheticResponseTimeDistribution"), prepareData(data.result.series, $("#choicesSyntheticResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshSyntheticResponseTimeDistribution() {
    var infos = syntheticResponseTimeDistributionInfos;
    prepareSeries(infos.data, true);
    if (isGraph($("#flotSyntheticResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerSyntheticResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var activeThreadsOverTimeInfos = {
        data: {"result": {"minY": 38.5505952380953, "minX": 1.75039062E12, "maxY": 40.0, "series": [{"data": [[1.75039086E12, 40.0], [1.75039068E12, 40.0], [1.7503908E12, 40.0], [1.75039062E12, 38.5505952380953], [1.75039092E12, 39.120903954802266], [1.75039074E12, 40.0]], "isOverall": false, "label": "线程组", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039092E12, "title": "Active Threads Over Time"}},
        getOptions: function() {
            return {
                series: {
                    stack: true,
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 6,
                    show: true,
                    container: '#legendActiveThreadsOverTime'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                selection: {
                    mode: 'xy'
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : At %x there were %y active threads"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesActiveThreadsOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotActiveThreadsOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewActiveThreadsOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Active Threads Over Time
function refreshActiveThreadsOverTime(fixTimestamps) {
    var infos = activeThreadsOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotActiveThreadsOverTime"))) {
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesActiveThreadsOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotActiveThreadsOverTime", "#overviewActiveThreadsOverTime");
        $('#footerActiveThreadsOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var timeVsThreadsInfos = {
        data: {"result": {"minY": 179.875, "minX": 1.0, "maxY": 1036.0, "series": [{"data": [[2.0, 280.0], [32.0, 653.0], [33.0, 596.5714285714286], [34.0, 611.25], [35.0, 630.5], [36.0, 649.375], [37.0, 659.7142857142857], [38.0, 669.5], [39.0, 581.8333333333334], [40.0, 680.8206681668688], [3.0, 237.0], [4.0, 625.0], [5.0, 210.0], [6.0, 179.875], [7.0, 196.85714285714283], [8.0, 219.16666666666666], [9.0, 224.28571428571428], [10.0, 237.625], [11.0, 280.0], [12.0, 407.2857142857143], [13.0, 291.1428571428571], [14.0, 313.83333333333337], [15.0, 317.75], [16.0, 339.14285714285717], [1.0, 1036.0], [17.0, 363.66666666666663], [18.0, 356.25], [19.0, 366.99999999999994], [20.0, 387.33333333333337], [21.0, 367.85714285714283], [22.0, 429.3333333333333], [23.0, 444.2857142857143], [24.0, 478.3333333333333], [25.0, 509.8333333333333], [26.0, 550.1666666666666], [27.0, 555.1666666666666], [28.0, 545.0], [29.0, 573.4285714285713], [30.0, 557.8888888888889], [31.0, 488.33333333333337]], "isOverall": false, "label": "HTTP请求配置", "isController": false}, {"data": [[39.73414481542587, 677.2999260565392]], "isOverall": false, "label": "HTTP请求配置-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 40.0, "title": "Time VS Threads"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: { noColumns: 2,show: true, container: '#legendTimeVsThreads' },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s: At %x.2 active threads, Average response time was %y.2 ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesTimeVsThreads"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotTimesVsThreads"), dataset, options);
            // setup overview
            $.plot($("#overviewTimesVsThreads"), dataset, prepareOverviewOptions(options));
        }
};

// Time vs threads
function refreshTimeVsThreads(){
    var infos = timeVsThreadsInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTimeVsThreads");
        return;
    }
    if(isGraph($("#flotTimesVsThreads"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTimeVsThreads");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTimesVsThreads", "#overviewTimesVsThreads");
        $('#footerTimeVsThreads .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var bytesThroughputOverTimeInfos = {
        data : {"result": {"minY": 3635.15, "minX": 1.75039062E12, "maxY": 42065.0, "series": [{"data": [[1.75039086E12, 41994.5], [1.75039068E12, 40066.53333333333], [1.7503908E12, 42065.0], [1.75039062E12, 31584.0], [1.75039092E12, 10398.75], [1.75039074E12, 40467.0]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.75039086E12, 14680.566666666668], [1.75039068E12, 14006.933333333332], [1.7503908E12, 14705.133333333333], [1.75039062E12, 11041.0], [1.75039092E12, 3635.15], [1.75039074E12, 14146.433333333332]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039092E12, "title": "Bytes Throughput Over Time"}},
        getOptions : function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity) ,
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Bytes / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendBytesThroughputOverTime'
                },
                selection: {
                    mode: "xy"
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y"
                }
            };
        },
        createGraph : function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesBytesThroughputOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotBytesThroughputOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewBytesThroughputOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Bytes throughput Over Time
function refreshBytesThroughputOverTime(fixTimestamps) {
    var infos = bytesThroughputOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotBytesThroughputOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesBytesThroughputOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotBytesThroughputOverTime", "#overviewBytesThroughputOverTime");
        $('#footerBytesThroughputOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimesOverTimeInfos = {
        data: {"result": {"minY": 625.7473958333353, "minX": 1.75039062E12, "maxY": 706.2225988700571, "series": [{"data": [[1.75039086E12, 671.2993844431995], [1.75039068E12, 703.6627565982418], [1.7503908E12, 669.8117318435745], [1.75039062E12, 625.7473958333353], [1.75039092E12, 706.2225988700571], [1.75039074E12, 698.0121951219506]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039092E12, "title": "Response Time Over Time"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average response time was %y ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Times Over Time
function refreshResponseTimeOverTime(fixTimestamps) {
    var infos = responseTimesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotResponseTimesOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesOverTime", "#overviewResponseTimesOverTime");
        $('#footerResponseTimesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var latenciesOverTimeInfos = {
        data: {"result": {"minY": 625.4490327380922, "minX": 1.75039062E12, "maxY": 705.8180790960449, "series": [{"data": [[1.75039086E12, 670.9124230554003], [1.75039068E12, 703.284164222872], [1.7503908E12, 669.52402234637], [1.75039062E12, 625.4490327380922], [1.75039092E12, 705.8180790960449], [1.75039074E12, 697.5325203252025]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039092E12, "title": "Latencies Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response latencies in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendLatenciesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average latency was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesLatenciesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotLatenciesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewLatenciesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Latencies Over Time
function refreshLatenciesOverTime(fixTimestamps) {
    var infos = latenciesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyLatenciesOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotLatenciesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesLatenciesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotLatenciesOverTime", "#overviewLatenciesOverTime");
        $('#footerLatenciesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var connectTimeOverTimeInfos = {
        data: {"result": {"minY": 0.0, "minX": 1.75039062E12, "maxY": 0.03645833333333318, "series": [{"data": [[1.75039086E12, 0.014549524342473374], [1.75039068E12, 0.016715542521994104], [1.7503908E12, 0.013128491620111675], [1.75039062E12, 0.03645833333333318], [1.75039092E12, 0.0], [1.75039074E12, 0.020325203252032523]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039092E12, "title": "Connect Time Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getConnectTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average Connect Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendConnectTimeOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average connect time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesConnectTimeOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotConnectTimeOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewConnectTimeOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Connect Time Over Time
function refreshConnectTimeOverTime(fixTimestamps) {
    var infos = connectTimeOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyConnectTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotConnectTimeOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesConnectTimeOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotConnectTimeOverTime", "#overviewConnectTimeOverTime");
        $('#footerConnectTimeOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var responseTimePercentilesOverTimeInfos = {
        data: {"result": {"minY": 92.0, "minX": 1.75039062E12, "maxY": 2108.0, "series": [{"data": [[1.75039086E12, 1881.0], [1.75039068E12, 1978.0], [1.7503908E12, 2108.0], [1.75039062E12, 1621.0], [1.75039092E12, 1775.0], [1.75039074E12, 2082.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.75039086E12, 805.0], [1.75039068E12, 887.9000000000001], [1.7503908E12, 780.0], [1.75039062E12, 773.0], [1.75039092E12, 855.1999999999999], [1.75039074E12, 855.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.75039086E12, 1327.25], [1.75039068E12, 1392.3399999999992], [1.7503908E12, 1242.0], [1.75039062E12, 1210.1100000000001], [1.75039092E12, 1388.1399999999999], [1.75039074E12, 1377.1000000000004]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.75039086E12, 1076.25], [1.75039068E12, 1152.4499999999998], [1.7503908E12, 901.0], [1.75039062E12, 837.5499999999997], [1.75039092E12, 1159.1], [1.75039074E12, 1083.25]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.75039086E12, 122.0], [1.75039068E12, 123.0], [1.7503908E12, 126.0], [1.75039062E12, 92.0], [1.75039092E12, 125.0], [1.75039074E12, 125.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.75039086E12, 654.0], [1.75039068E12, 683.0], [1.7503908E12, 653.0], [1.75039062E12, 619.0], [1.75039092E12, 694.0], [1.75039074E12, 679.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039092E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Response Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentilesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Response time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentilesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimePercentilesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimePercentilesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Time Percentiles Over Time
function refreshResponseTimePercentilesOverTime(fixTimestamps) {
    var infos = responseTimePercentilesOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotResponseTimePercentilesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimePercentilesOverTime", "#overviewResponseTimePercentilesOverTime");
        $('#footerResponseTimePercentilesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var responseTimeVsRequestInfos = {
    data: {"result": {"minY": 119.0, "minX": 25.0, "maxY": 960.0, "series": [{"data": [[39.0, 920.0], [38.0, 627.0], [41.0, 939.5], [42.0, 889.0], [43.0, 960.0], [44.0, 874.5], [45.0, 795.0], [47.0, 641.0], [46.0, 806.0], [48.0, 771.0], [49.0, 770.5], [50.0, 803.0], [51.0, 788.0], [52.0, 749.5], [53.0, 757.0], [54.0, 725.0], [55.0, 724.0], [57.0, 680.0], [56.0, 699.0], [59.0, 671.0], [58.0, 677.0], [60.0, 649.0], [61.0, 649.0], [62.0, 644.0], [63.0, 639.0], [67.0, 600.0], [64.0, 628.0], [65.0, 627.0], [66.0, 624.0], [70.0, 595.0], [69.0, 614.0], [68.0, 616.5], [25.0, 119.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 70.0, "title": "Response Time Vs Request"}},
    getOptions: function() {
        return {
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Response Time in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: {
                noColumns: 2,
                show: true,
                container: '#legendResponseTimeVsRequest'
            },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median response time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesResponseTimeVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotResponseTimeVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewResponseTimeVsRequest"), dataset, prepareOverviewOptions(options));

    }
};

// Response Time vs Request
function refreshResponseTimeVsRequest() {
    var infos = responseTimeVsRequestInfos;
    prepareSeries(infos.data);
    if (isGraph($("#flotResponseTimeVsRequest"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeVsRequest");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimeVsRequest", "#overviewResponseTimeVsRequest");
        $('#footerResponseRimeVsRequest .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var latenciesVsRequestInfos = {
    data: {"result": {"minY": 119.0, "minX": 25.0, "maxY": 960.0, "series": [{"data": [[39.0, 919.0], [38.0, 627.0], [41.0, 934.5], [42.0, 888.5], [43.0, 960.0], [44.0, 874.0], [45.0, 795.0], [47.0, 641.0], [46.0, 806.0], [48.0, 770.0], [49.0, 770.0], [50.0, 803.0], [51.0, 788.0], [52.0, 749.5], [53.0, 757.0], [54.0, 724.5], [55.0, 724.0], [57.0, 680.0], [56.0, 698.0], [59.0, 671.0], [58.0, 676.0], [60.0, 649.0], [61.0, 649.0], [62.0, 643.0], [63.0, 639.0], [67.0, 600.0], [64.0, 628.0], [65.0, 626.0], [66.0, 624.0], [70.0, 595.0], [69.0, 614.0], [68.0, 616.0], [25.0, 119.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 70.0, "title": "Latencies Vs Request"}},
    getOptions: function() {
        return{
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Latency in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: { noColumns: 2,show: true, container: '#legendLatencyVsRequest' },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median Latency time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesLatencyVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotLatenciesVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewLatenciesVsRequest"), dataset, prepareOverviewOptions(options));
    }
};

// Latencies vs Request
function refreshLatenciesVsRequest() {
        var infos = latenciesVsRequestInfos;
        prepareSeries(infos.data);
        if(isGraph($("#flotLatenciesVsRequest"))){
            infos.createGraph();
        }else{
            var choiceContainer = $("#choicesLatencyVsRequest");
            createLegend(choiceContainer, infos);
            infos.createGraph();
            setGraphZoomable("#flotLatenciesVsRequest", "#overviewLatenciesVsRequest");
            $('#footerLatenciesVsRequest .legendColorBox > div').each(function(i){
                $(this).clone().prependTo(choiceContainer.find("li").eq(i));
            });
        }
};

var hitsPerSecondInfos = {
        data: {"result": {"minY": 14.083333333333334, "minX": 1.75039062E12, "maxY": 59.666666666666664, "series": [{"data": [[1.75039086E12, 59.56666666666667], [1.75039068E12, 56.833333333333336], [1.7503908E12, 59.666666666666664], [1.75039062E12, 45.46666666666667], [1.75039092E12, 14.083333333333334], [1.75039074E12, 57.4]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039092E12, "title": "Hits Per Second"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of hits / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendHitsPerSecond"
                },
                selection: {
                    mode : 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y.2 hits/sec"
                }
            };
        },
        createGraph: function createGraph() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesHitsPerSecond"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotHitsPerSecond"), dataset, options);
            // setup overview
            $.plot($("#overviewHitsPerSecond"), dataset, prepareOverviewOptions(options));
        }
};

// Hits per second
function refreshHitsPerSecond(fixTimestamps) {
    var infos = hitsPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if (isGraph($("#flotHitsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesHitsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotHitsPerSecond", "#overviewHitsPerSecond");
        $('#footerHitsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var codesPerSecondInfos = {
        data: {"result": {"minY": 14.75, "minX": 1.75039062E12, "maxY": 59.666666666666664, "series": [{"data": [[1.75039086E12, 59.56666666666667], [1.75039068E12, 56.833333333333336], [1.7503908E12, 59.666666666666664], [1.75039062E12, 44.8], [1.75039092E12, 14.75], [1.75039074E12, 57.4]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039092E12, "title": "Codes Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendCodesPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "Number of Response Codes %s at %x was %y.2 responses / sec"
                }
            };
        },
    createGraph: function() {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesCodesPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotCodesPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewCodesPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Codes per second
function refreshCodesPerSecond(fixTimestamps) {
    var infos = codesPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotCodesPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesCodesPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotCodesPerSecond", "#overviewCodesPerSecond");
        $('#footerCodesPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var transactionsPerSecondInfos = {
        data: {"result": {"minY": 14.75, "minX": 1.75039062E12, "maxY": 59.666666666666664, "series": [{"data": [[1.75039086E12, 59.56666666666667], [1.75039068E12, 56.833333333333336], [1.7503908E12, 59.666666666666664], [1.75039062E12, 44.8], [1.75039092E12, 14.75], [1.75039074E12, 57.4]], "isOverall": false, "label": "HTTP请求配置-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039092E12, "title": "Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTransactionsPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                }
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTransactionsPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTransactionsPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewTransactionsPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Transactions per second
function refreshTransactionsPerSecond(fixTimestamps) {
    var infos = transactionsPerSecondInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTransactionsPerSecond");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotTransactionsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTransactionsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTransactionsPerSecond", "#overviewTransactionsPerSecond");
        $('#footerTransactionsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var totalTPSInfos = {
        data: {"result": {"minY": 14.75, "minX": 1.75039062E12, "maxY": 59.666666666666664, "series": [{"data": [[1.75039086E12, 59.56666666666667], [1.75039068E12, 56.833333333333336], [1.7503908E12, 59.666666666666664], [1.75039062E12, 44.8], [1.75039092E12, 14.75], [1.75039074E12, 57.4]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039092E12, "title": "Total Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTotalTPS"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                },
                colors: ["#9ACD32", "#FF6347"]
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTotalTPS"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTotalTPS"), dataset, options);
        // setup overview
        $.plot($("#overviewTotalTPS"), dataset, prepareOverviewOptions(options));
    }
};

// Total Transactions per second
function refreshTotalTPS(fixTimestamps) {
    var infos = totalTPSInfos;
    // We want to ignore seriesFilter
    prepareSeries(infos.data, false, true);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 28800000);
    }
    if(isGraph($("#flotTotalTPS"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTotalTPS");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTotalTPS", "#overviewTotalTPS");
        $('#footerTotalTPS .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

// Collapse the graph matching the specified DOM element depending the collapsed
// status
function collapse(elem, collapsed){
    if(collapsed){
        $(elem).parent().find(".fa-chevron-up").removeClass("fa-chevron-up").addClass("fa-chevron-down");
    } else {
        $(elem).parent().find(".fa-chevron-down").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        if (elem.id == "bodyBytesThroughputOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshBytesThroughputOverTime(true);
            }
            document.location.href="#bytesThroughputOverTime";
        } else if (elem.id == "bodyLatenciesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesOverTime(true);
            }
            document.location.href="#latenciesOverTime";
        } else if (elem.id == "bodyCustomGraph") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCustomGraph(true);
            }
            document.location.href="#responseCustomGraph";
        } else if (elem.id == "bodyConnectTimeOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshConnectTimeOverTime(true);
            }
            document.location.href="#connectTimeOverTime";
        } else if (elem.id == "bodyResponseTimePercentilesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimePercentilesOverTime(true);
            }
            document.location.href="#responseTimePercentilesOverTime";
        } else if (elem.id == "bodyResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeDistribution();
            }
            document.location.href="#responseTimeDistribution" ;
        } else if (elem.id == "bodySyntheticResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshSyntheticResponseTimeDistribution();
            }
            document.location.href="#syntheticResponseTimeDistribution" ;
        } else if (elem.id == "bodyActiveThreadsOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshActiveThreadsOverTime(true);
            }
            document.location.href="#activeThreadsOverTime";
        } else if (elem.id == "bodyTimeVsThreads") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTimeVsThreads();
            }
            document.location.href="#timeVsThreads" ;
        } else if (elem.id == "bodyCodesPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCodesPerSecond(true);
            }
            document.location.href="#codesPerSecond";
        } else if (elem.id == "bodyTransactionsPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTransactionsPerSecond(true);
            }
            document.location.href="#transactionsPerSecond";
        } else if (elem.id == "bodyTotalTPS") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTotalTPS(true);
            }
            document.location.href="#totalTPS";
        } else if (elem.id == "bodyResponseTimeVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeVsRequest();
            }
            document.location.href="#responseTimeVsRequest";
        } else if (elem.id == "bodyLatenciesVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesVsRequest();
            }
            document.location.href="#latencyVsRequest";
        }
    }
}

/*
 * Activates or deactivates all series of the specified graph (represented by id parameter)
 * depending on checked argument.
 */
function toggleAll(id, checked){
    var placeholder = document.getElementById(id);

    var cases = $(placeholder).find(':checkbox');
    cases.prop('checked', checked);
    $(cases).parent().children().children().toggleClass("legend-disabled", !checked);

    var choiceContainer;
    if ( id == "choicesBytesThroughputOverTime"){
        choiceContainer = $("#choicesBytesThroughputOverTime");
        refreshBytesThroughputOverTime(false);
    } else if(id == "choicesResponseTimesOverTime"){
        choiceContainer = $("#choicesResponseTimesOverTime");
        refreshResponseTimeOverTime(false);
    }else if(id == "choicesResponseCustomGraph"){
        choiceContainer = $("#choicesResponseCustomGraph");
        refreshCustomGraph(false);
    } else if ( id == "choicesLatenciesOverTime"){
        choiceContainer = $("#choicesLatenciesOverTime");
        refreshLatenciesOverTime(false);
    } else if ( id == "choicesConnectTimeOverTime"){
        choiceContainer = $("#choicesConnectTimeOverTime");
        refreshConnectTimeOverTime(false);
    } else if ( id == "choicesResponseTimePercentilesOverTime"){
        choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        refreshResponseTimePercentilesOverTime(false);
    } else if ( id == "choicesResponseTimePercentiles"){
        choiceContainer = $("#choicesResponseTimePercentiles");
        refreshResponseTimePercentiles();
    } else if(id == "choicesActiveThreadsOverTime"){
        choiceContainer = $("#choicesActiveThreadsOverTime");
        refreshActiveThreadsOverTime(false);
    } else if ( id == "choicesTimeVsThreads"){
        choiceContainer = $("#choicesTimeVsThreads");
        refreshTimeVsThreads();
    } else if ( id == "choicesSyntheticResponseTimeDistribution"){
        choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        refreshSyntheticResponseTimeDistribution();
    } else if ( id == "choicesResponseTimeDistribution"){
        choiceContainer = $("#choicesResponseTimeDistribution");
        refreshResponseTimeDistribution();
    } else if ( id == "choicesHitsPerSecond"){
        choiceContainer = $("#choicesHitsPerSecond");
        refreshHitsPerSecond(false);
    } else if(id == "choicesCodesPerSecond"){
        choiceContainer = $("#choicesCodesPerSecond");
        refreshCodesPerSecond(false);
    } else if ( id == "choicesTransactionsPerSecond"){
        choiceContainer = $("#choicesTransactionsPerSecond");
        refreshTransactionsPerSecond(false);
    } else if ( id == "choicesTotalTPS"){
        choiceContainer = $("#choicesTotalTPS");
        refreshTotalTPS(false);
    } else if ( id == "choicesResponseTimeVsRequest"){
        choiceContainer = $("#choicesResponseTimeVsRequest");
        refreshResponseTimeVsRequest();
    } else if ( id == "choicesLatencyVsRequest"){
        choiceContainer = $("#choicesLatencyVsRequest");
        refreshLatenciesVsRequest();
    }
    var color = checked ? "black" : "#818181";
    if(choiceContainer != null) {
        choiceContainer.find("label").each(function(){
            this.style.color = color;
        });
    }
}

