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
        data: {"result": {"minY": 86.0, "minX": 0.0, "maxY": 2537.0, "series": [{"data": [[0.0, 86.0], [0.1, 117.0], [0.2, 126.0], [0.3, 130.0], [0.4, 132.0], [0.5, 134.0], [0.6, 136.0], [0.7, 138.0], [0.8, 139.0], [0.9, 141.0], [1.0, 142.0], [1.1, 143.0], [1.2, 145.0], [1.3, 146.0], [1.4, 148.0], [1.5, 149.0], [1.6, 150.0], [1.7, 151.0], [1.8, 152.0], [1.9, 154.0], [2.0, 156.0], [2.1, 157.0], [2.2, 159.0], [2.3, 160.0], [2.4, 161.0], [2.5, 163.0], [2.6, 166.0], [2.7, 167.0], [2.8, 168.0], [2.9, 170.0], [3.0, 172.0], [3.1, 173.0], [3.2, 175.0], [3.3, 177.0], [3.4, 179.0], [3.5, 180.0], [3.6, 182.0], [3.7, 183.0], [3.8, 186.0], [3.9, 189.0], [4.0, 191.0], [4.1, 193.0], [4.2, 197.0], [4.3, 200.0], [4.4, 204.0], [4.5, 208.0], [4.6, 212.0], [4.7, 216.0], [4.8, 223.0], [4.9, 229.0], [5.0, 235.0], [5.1, 244.0], [5.2, 254.0], [5.3, 274.0], [5.4, 298.0], [5.5, 336.0], [5.6, 361.0], [5.7, 398.0], [5.8, 438.0], [5.9, 508.0], [6.0, 587.0], [6.1, 593.0], [6.2, 597.0], [6.3, 601.0], [6.4, 602.0], [6.5, 604.0], [6.6, 606.0], [6.7, 607.0], [6.8, 608.0], [6.9, 610.0], [7.0, 611.0], [7.1, 612.0], [7.2, 614.0], [7.3, 614.0], [7.4, 615.0], [7.5, 616.0], [7.6, 617.0], [7.7, 619.0], [7.8, 619.0], [7.9, 620.0], [8.0, 621.0], [8.1, 621.0], [8.2, 622.0], [8.3, 623.0], [8.4, 623.0], [8.5, 624.0], [8.6, 624.0], [8.7, 625.0], [8.8, 626.0], [8.9, 626.0], [9.0, 627.0], [9.1, 627.0], [9.2, 628.0], [9.3, 628.0], [9.4, 629.0], [9.5, 630.0], [9.6, 630.0], [9.7, 631.0], [9.8, 631.0], [9.9, 632.0], [10.0, 632.0], [10.1, 633.0], [10.2, 634.0], [10.3, 634.0], [10.4, 634.0], [10.5, 635.0], [10.6, 636.0], [10.7, 636.0], [10.8, 636.0], [10.9, 637.0], [11.0, 637.0], [11.1, 638.0], [11.2, 638.0], [11.3, 639.0], [11.4, 639.0], [11.5, 640.0], [11.6, 640.0], [11.7, 641.0], [11.8, 641.0], [11.9, 642.0], [12.0, 642.0], [12.1, 643.0], [12.2, 643.0], [12.3, 643.0], [12.4, 644.0], [12.5, 644.0], [12.6, 645.0], [12.7, 645.0], [12.8, 646.0], [12.9, 646.0], [13.0, 647.0], [13.1, 647.0], [13.2, 647.0], [13.3, 648.0], [13.4, 648.0], [13.5, 648.0], [13.6, 649.0], [13.7, 649.0], [13.8, 650.0], [13.9, 650.0], [14.0, 650.0], [14.1, 651.0], [14.2, 651.0], [14.3, 652.0], [14.4, 652.0], [14.5, 653.0], [14.6, 653.0], [14.7, 653.0], [14.8, 654.0], [14.9, 654.0], [15.0, 655.0], [15.1, 655.0], [15.2, 655.0], [15.3, 656.0], [15.4, 656.0], [15.5, 656.0], [15.6, 657.0], [15.7, 657.0], [15.8, 657.0], [15.9, 657.0], [16.0, 658.0], [16.1, 658.0], [16.2, 659.0], [16.3, 659.0], [16.4, 659.0], [16.5, 659.0], [16.6, 660.0], [16.7, 660.0], [16.8, 660.0], [16.9, 661.0], [17.0, 661.0], [17.1, 661.0], [17.2, 661.0], [17.3, 662.0], [17.4, 662.0], [17.5, 662.0], [17.6, 663.0], [17.7, 663.0], [17.8, 663.0], [17.9, 664.0], [18.0, 664.0], [18.1, 664.0], [18.2, 664.0], [18.3, 665.0], [18.4, 665.0], [18.5, 665.0], [18.6, 665.0], [18.7, 666.0], [18.8, 666.0], [18.9, 666.0], [19.0, 666.0], [19.1, 667.0], [19.2, 667.0], [19.3, 667.0], [19.4, 667.0], [19.5, 668.0], [19.6, 668.0], [19.7, 668.0], [19.8, 668.0], [19.9, 669.0], [20.0, 669.0], [20.1, 669.0], [20.2, 670.0], [20.3, 670.0], [20.4, 670.0], [20.5, 670.0], [20.6, 671.0], [20.7, 671.0], [20.8, 671.0], [20.9, 672.0], [21.0, 672.0], [21.1, 672.0], [21.2, 672.0], [21.3, 673.0], [21.4, 673.0], [21.5, 673.0], [21.6, 673.0], [21.7, 674.0], [21.8, 674.0], [21.9, 674.0], [22.0, 674.0], [22.1, 675.0], [22.2, 675.0], [22.3, 675.0], [22.4, 675.0], [22.5, 675.0], [22.6, 676.0], [22.7, 676.0], [22.8, 676.0], [22.9, 676.0], [23.0, 677.0], [23.1, 677.0], [23.2, 677.0], [23.3, 678.0], [23.4, 678.0], [23.5, 678.0], [23.6, 678.0], [23.7, 679.0], [23.8, 679.0], [23.9, 679.0], [24.0, 679.0], [24.1, 680.0], [24.2, 680.0], [24.3, 680.0], [24.4, 681.0], [24.5, 681.0], [24.6, 681.0], [24.7, 681.0], [24.8, 682.0], [24.9, 682.0], [25.0, 682.0], [25.1, 683.0], [25.2, 683.0], [25.3, 683.0], [25.4, 683.0], [25.5, 683.0], [25.6, 684.0], [25.7, 684.0], [25.8, 684.0], [25.9, 684.0], [26.0, 685.0], [26.1, 685.0], [26.2, 685.0], [26.3, 685.0], [26.4, 686.0], [26.5, 686.0], [26.6, 686.0], [26.7, 686.0], [26.8, 686.0], [26.9, 686.0], [27.0, 687.0], [27.1, 687.0], [27.2, 687.0], [27.3, 687.0], [27.4, 688.0], [27.5, 688.0], [27.6, 688.0], [27.7, 688.0], [27.8, 689.0], [27.9, 689.0], [28.0, 689.0], [28.1, 689.0], [28.2, 689.0], [28.3, 689.0], [28.4, 690.0], [28.5, 690.0], [28.6, 690.0], [28.7, 690.0], [28.8, 691.0], [28.9, 691.0], [29.0, 691.0], [29.1, 691.0], [29.2, 692.0], [29.3, 692.0], [29.4, 692.0], [29.5, 692.0], [29.6, 692.0], [29.7, 693.0], [29.8, 693.0], [29.9, 693.0], [30.0, 693.0], [30.1, 693.0], [30.2, 694.0], [30.3, 694.0], [30.4, 694.0], [30.5, 694.0], [30.6, 694.0], [30.7, 695.0], [30.8, 695.0], [30.9, 695.0], [31.0, 695.0], [31.1, 695.0], [31.2, 696.0], [31.3, 696.0], [31.4, 696.0], [31.5, 696.0], [31.6, 697.0], [31.7, 697.0], [31.8, 697.0], [31.9, 697.0], [32.0, 697.0], [32.1, 697.0], [32.2, 698.0], [32.3, 698.0], [32.4, 698.0], [32.5, 698.0], [32.6, 699.0], [32.7, 699.0], [32.8, 699.0], [32.9, 699.0], [33.0, 699.0], [33.1, 699.0], [33.2, 700.0], [33.3, 700.0], [33.4, 700.0], [33.5, 700.0], [33.6, 700.0], [33.7, 701.0], [33.8, 701.0], [33.9, 701.0], [34.0, 701.0], [34.1, 701.0], [34.2, 702.0], [34.3, 702.0], [34.4, 702.0], [34.5, 702.0], [34.6, 702.0], [34.7, 703.0], [34.8, 703.0], [34.9, 703.0], [35.0, 703.0], [35.1, 703.0], [35.2, 703.0], [35.3, 704.0], [35.4, 704.0], [35.5, 704.0], [35.6, 704.0], [35.7, 704.0], [35.8, 705.0], [35.9, 705.0], [36.0, 705.0], [36.1, 705.0], [36.2, 705.0], [36.3, 706.0], [36.4, 706.0], [36.5, 706.0], [36.6, 706.0], [36.7, 706.0], [36.8, 706.0], [36.9, 707.0], [37.0, 707.0], [37.1, 707.0], [37.2, 707.0], [37.3, 707.0], [37.4, 707.0], [37.5, 708.0], [37.6, 708.0], [37.7, 708.0], [37.8, 708.0], [37.9, 708.0], [38.0, 708.0], [38.1, 709.0], [38.2, 709.0], [38.3, 709.0], [38.4, 709.0], [38.5, 709.0], [38.6, 710.0], [38.7, 710.0], [38.8, 710.0], [38.9, 710.0], [39.0, 710.0], [39.1, 710.0], [39.2, 710.0], [39.3, 711.0], [39.4, 711.0], [39.5, 711.0], [39.6, 711.0], [39.7, 711.0], [39.8, 711.0], [39.9, 712.0], [40.0, 712.0], [40.1, 712.0], [40.2, 712.0], [40.3, 712.0], [40.4, 712.0], [40.5, 713.0], [40.6, 713.0], [40.7, 713.0], [40.8, 713.0], [40.9, 713.0], [41.0, 713.0], [41.1, 714.0], [41.2, 714.0], [41.3, 714.0], [41.4, 714.0], [41.5, 715.0], [41.6, 715.0], [41.7, 715.0], [41.8, 715.0], [41.9, 715.0], [42.0, 715.0], [42.1, 716.0], [42.2, 716.0], [42.3, 716.0], [42.4, 716.0], [42.5, 716.0], [42.6, 716.0], [42.7, 716.0], [42.8, 717.0], [42.9, 717.0], [43.0, 717.0], [43.1, 717.0], [43.2, 717.0], [43.3, 717.0], [43.4, 718.0], [43.5, 718.0], [43.6, 718.0], [43.7, 718.0], [43.8, 718.0], [43.9, 719.0], [44.0, 719.0], [44.1, 719.0], [44.2, 719.0], [44.3, 719.0], [44.4, 719.0], [44.5, 719.0], [44.6, 720.0], [44.7, 720.0], [44.8, 720.0], [44.9, 720.0], [45.0, 720.0], [45.1, 720.0], [45.2, 721.0], [45.3, 721.0], [45.4, 721.0], [45.5, 721.0], [45.6, 721.0], [45.7, 721.0], [45.8, 722.0], [45.9, 722.0], [46.0, 722.0], [46.1, 722.0], [46.2, 722.0], [46.3, 722.0], [46.4, 723.0], [46.5, 723.0], [46.6, 723.0], [46.7, 723.0], [46.8, 723.0], [46.9, 724.0], [47.0, 724.0], [47.1, 724.0], [47.2, 724.0], [47.3, 724.0], [47.4, 724.0], [47.5, 724.0], [47.6, 725.0], [47.7, 725.0], [47.8, 725.0], [47.9, 725.0], [48.0, 725.0], [48.1, 725.0], [48.2, 726.0], [48.3, 726.0], [48.4, 726.0], [48.5, 726.0], [48.6, 726.0], [48.7, 727.0], [48.8, 727.0], [48.9, 727.0], [49.0, 727.0], [49.1, 727.0], [49.2, 727.0], [49.3, 728.0], [49.4, 728.0], [49.5, 728.0], [49.6, 728.0], [49.7, 728.0], [49.8, 728.0], [49.9, 729.0], [50.0, 729.0], [50.1, 729.0], [50.2, 729.0], [50.3, 729.0], [50.4, 729.0], [50.5, 730.0], [50.6, 730.0], [50.7, 730.0], [50.8, 730.0], [50.9, 730.0], [51.0, 731.0], [51.1, 731.0], [51.2, 731.0], [51.3, 731.0], [51.4, 731.0], [51.5, 731.0], [51.6, 732.0], [51.7, 732.0], [51.8, 732.0], [51.9, 732.0], [52.0, 732.0], [52.1, 733.0], [52.2, 733.0], [52.3, 733.0], [52.4, 733.0], [52.5, 733.0], [52.6, 734.0], [52.7, 734.0], [52.8, 734.0], [52.9, 734.0], [53.0, 734.0], [53.1, 734.0], [53.2, 735.0], [53.3, 735.0], [53.4, 735.0], [53.5, 735.0], [53.6, 735.0], [53.7, 736.0], [53.8, 736.0], [53.9, 736.0], [54.0, 736.0], [54.1, 736.0], [54.2, 737.0], [54.3, 737.0], [54.4, 737.0], [54.5, 737.0], [54.6, 737.0], [54.7, 737.0], [54.8, 738.0], [54.9, 738.0], [55.0, 738.0], [55.1, 738.0], [55.2, 738.0], [55.3, 738.0], [55.4, 738.0], [55.5, 739.0], [55.6, 739.0], [55.7, 739.0], [55.8, 739.0], [55.9, 739.0], [56.0, 740.0], [56.1, 740.0], [56.2, 740.0], [56.3, 740.0], [56.4, 740.0], [56.5, 741.0], [56.6, 741.0], [56.7, 741.0], [56.8, 741.0], [56.9, 742.0], [57.0, 742.0], [57.1, 742.0], [57.2, 742.0], [57.3, 743.0], [57.4, 743.0], [57.5, 743.0], [57.6, 743.0], [57.7, 743.0], [57.8, 744.0], [57.9, 744.0], [58.0, 744.0], [58.1, 744.0], [58.2, 744.0], [58.3, 745.0], [58.4, 745.0], [58.5, 745.0], [58.6, 745.0], [58.7, 745.0], [58.8, 746.0], [58.9, 746.0], [59.0, 746.0], [59.1, 746.0], [59.2, 746.0], [59.3, 747.0], [59.4, 747.0], [59.5, 747.0], [59.6, 747.0], [59.7, 747.0], [59.8, 748.0], [59.9, 748.0], [60.0, 748.0], [60.1, 748.0], [60.2, 748.0], [60.3, 749.0], [60.4, 749.0], [60.5, 749.0], [60.6, 749.0], [60.7, 749.0], [60.8, 750.0], [60.9, 750.0], [61.0, 750.0], [61.1, 750.0], [61.2, 750.0], [61.3, 751.0], [61.4, 751.0], [61.5, 751.0], [61.6, 751.0], [61.7, 752.0], [61.8, 752.0], [61.9, 752.0], [62.0, 752.0], [62.1, 752.0], [62.2, 753.0], [62.3, 753.0], [62.4, 753.0], [62.5, 753.0], [62.6, 753.0], [62.7, 754.0], [62.8, 754.0], [62.9, 754.0], [63.0, 754.0], [63.1, 755.0], [63.2, 755.0], [63.3, 755.0], [63.4, 755.0], [63.5, 755.0], [63.6, 756.0], [63.7, 756.0], [63.8, 756.0], [63.9, 756.0], [64.0, 756.0], [64.1, 757.0], [64.2, 757.0], [64.3, 757.0], [64.4, 757.0], [64.5, 758.0], [64.6, 758.0], [64.7, 758.0], [64.8, 758.0], [64.9, 758.0], [65.0, 759.0], [65.1, 759.0], [65.2, 759.0], [65.3, 760.0], [65.4, 760.0], [65.5, 760.0], [65.6, 760.0], [65.7, 760.0], [65.8, 761.0], [65.9, 761.0], [66.0, 761.0], [66.1, 761.0], [66.2, 762.0], [66.3, 762.0], [66.4, 762.0], [66.5, 763.0], [66.6, 763.0], [66.7, 763.0], [66.8, 763.0], [66.9, 764.0], [67.0, 764.0], [67.1, 764.0], [67.2, 765.0], [67.3, 765.0], [67.4, 765.0], [67.5, 765.0], [67.6, 766.0], [67.7, 766.0], [67.8, 766.0], [67.9, 767.0], [68.0, 767.0], [68.1, 767.0], [68.2, 767.0], [68.3, 768.0], [68.4, 768.0], [68.5, 768.0], [68.6, 768.0], [68.7, 769.0], [68.8, 769.0], [68.9, 770.0], [69.0, 770.0], [69.1, 770.0], [69.2, 770.0], [69.3, 770.0], [69.4, 771.0], [69.5, 771.0], [69.6, 771.0], [69.7, 772.0], [69.8, 772.0], [69.9, 772.0], [70.0, 773.0], [70.1, 773.0], [70.2, 773.0], [70.3, 774.0], [70.4, 774.0], [70.5, 774.0], [70.6, 774.0], [70.7, 775.0], [70.8, 775.0], [70.9, 775.0], [71.0, 775.0], [71.1, 776.0], [71.2, 776.0], [71.3, 776.0], [71.4, 777.0], [71.5, 777.0], [71.6, 778.0], [71.7, 778.0], [71.8, 778.0], [71.9, 779.0], [72.0, 779.0], [72.1, 779.0], [72.2, 780.0], [72.3, 780.0], [72.4, 780.0], [72.5, 781.0], [72.6, 781.0], [72.7, 781.0], [72.8, 782.0], [72.9, 782.0], [73.0, 783.0], [73.1, 783.0], [73.2, 783.0], [73.3, 784.0], [73.4, 784.0], [73.5, 784.0], [73.6, 785.0], [73.7, 785.0], [73.8, 785.0], [73.9, 786.0], [74.0, 786.0], [74.1, 787.0], [74.2, 788.0], [74.3, 788.0], [74.4, 788.0], [74.5, 789.0], [74.6, 789.0], [74.7, 789.0], [74.8, 790.0], [74.9, 790.0], [75.0, 790.0], [75.1, 791.0], [75.2, 791.0], [75.3, 792.0], [75.4, 792.0], [75.5, 793.0], [75.6, 793.0], [75.7, 793.0], [75.8, 794.0], [75.9, 794.0], [76.0, 795.0], [76.1, 795.0], [76.2, 795.0], [76.3, 796.0], [76.4, 796.0], [76.5, 797.0], [76.6, 797.0], [76.7, 798.0], [76.8, 799.0], [76.9, 799.0], [77.0, 799.0], [77.1, 800.0], [77.2, 800.0], [77.3, 801.0], [77.4, 801.0], [77.5, 802.0], [77.6, 802.0], [77.7, 803.0], [77.8, 803.0], [77.9, 804.0], [78.0, 804.0], [78.1, 804.0], [78.2, 805.0], [78.3, 805.0], [78.4, 806.0], [78.5, 806.0], [78.6, 807.0], [78.7, 807.0], [78.8, 808.0], [78.9, 809.0], [79.0, 809.0], [79.1, 810.0], [79.2, 811.0], [79.3, 811.0], [79.4, 812.0], [79.5, 813.0], [79.6, 813.0], [79.7, 814.0], [79.8, 815.0], [79.9, 816.0], [80.0, 816.0], [80.1, 817.0], [80.2, 818.0], [80.3, 819.0], [80.4, 820.0], [80.5, 820.0], [80.6, 821.0], [80.7, 822.0], [80.8, 823.0], [80.9, 823.0], [81.0, 824.0], [81.1, 825.0], [81.2, 826.0], [81.3, 826.0], [81.4, 827.0], [81.5, 828.0], [81.6, 829.0], [81.7, 829.0], [81.8, 830.0], [81.9, 831.0], [82.0, 831.0], [82.1, 832.0], [82.2, 833.0], [82.3, 833.0], [82.4, 834.0], [82.5, 835.0], [82.6, 836.0], [82.7, 837.0], [82.8, 837.0], [82.9, 838.0], [83.0, 839.0], [83.1, 840.0], [83.2, 842.0], [83.3, 842.0], [83.4, 843.0], [83.5, 844.0], [83.6, 845.0], [83.7, 846.0], [83.8, 846.0], [83.9, 847.0], [84.0, 848.0], [84.1, 849.0], [84.2, 850.0], [84.3, 851.0], [84.4, 852.0], [84.5, 853.0], [84.6, 854.0], [84.7, 855.0], [84.8, 856.0], [84.9, 857.0], [85.0, 859.0], [85.1, 860.0], [85.2, 861.0], [85.3, 862.0], [85.4, 864.0], [85.5, 864.0], [85.6, 866.0], [85.7, 866.0], [85.8, 868.0], [85.9, 869.0], [86.0, 870.0], [86.1, 871.0], [86.2, 872.0], [86.3, 873.0], [86.4, 874.0], [86.5, 875.0], [86.6, 877.0], [86.7, 877.0], [86.8, 879.0], [86.9, 880.0], [87.0, 882.0], [87.1, 883.0], [87.2, 884.0], [87.3, 885.0], [87.4, 886.0], [87.5, 887.0], [87.6, 889.0], [87.7, 890.0], [87.8, 891.0], [87.9, 892.0], [88.0, 894.0], [88.1, 895.0], [88.2, 896.0], [88.3, 898.0], [88.4, 899.0], [88.5, 900.0], [88.6, 902.0], [88.7, 903.0], [88.8, 904.0], [88.9, 906.0], [89.0, 907.0], [89.1, 908.0], [89.2, 910.0], [89.3, 911.0], [89.4, 912.0], [89.5, 913.0], [89.6, 915.0], [89.7, 916.0], [89.8, 917.0], [89.9, 919.0], [90.0, 921.0], [90.1, 923.0], [90.2, 924.0], [90.3, 925.0], [90.4, 927.0], [90.5, 929.0], [90.6, 930.0], [90.7, 932.0], [90.8, 934.0], [90.9, 936.0], [91.0, 938.0], [91.1, 940.0], [91.2, 942.0], [91.3, 943.0], [91.4, 945.0], [91.5, 947.0], [91.6, 949.0], [91.7, 951.0], [91.8, 954.0], [91.9, 956.0], [92.0, 958.0], [92.1, 959.0], [92.2, 960.0], [92.3, 961.0], [92.4, 963.0], [92.5, 966.0], [92.6, 968.0], [92.7, 969.0], [92.8, 972.0], [92.9, 974.0], [93.0, 978.0], [93.1, 980.0], [93.2, 984.0], [93.3, 987.0], [93.4, 990.0], [93.5, 993.0], [93.6, 997.0], [93.7, 1000.0], [93.8, 1005.0], [93.9, 1010.0], [94.0, 1015.0], [94.1, 1019.0], [94.2, 1023.0], [94.3, 1029.0], [94.4, 1035.0], [94.5, 1041.0], [94.6, 1048.0], [94.7, 1055.0], [94.8, 1067.0], [94.9, 1085.0], [95.0, 1115.0], [95.1, 1145.0], [95.2, 1172.0], [95.3, 1184.0], [95.4, 1195.0], [95.5, 1202.0], [95.6, 1209.0], [95.7, 1213.0], [95.8, 1217.0], [95.9, 1221.0], [96.0, 1229.0], [96.1, 1232.0], [96.2, 1238.0], [96.3, 1244.0], [96.4, 1248.0], [96.5, 1252.0], [96.6, 1256.0], [96.7, 1259.0], [96.8, 1263.0], [96.9, 1268.0], [97.0, 1271.0], [97.1, 1277.0], [97.2, 1281.0], [97.3, 1286.0], [97.4, 1290.0], [97.5, 1295.0], [97.6, 1300.0], [97.7, 1306.0], [97.8, 1311.0], [97.9, 1322.0], [98.0, 1325.0], [98.1, 1333.0], [98.2, 1338.0], [98.3, 1348.0], [98.4, 1359.0], [98.5, 1371.0], [98.6, 1383.0], [98.7, 1403.0], [98.8, 1419.0], [98.9, 1438.0], [99.0, 1458.0], [99.1, 1481.0], [99.2, 1499.0], [99.3, 1524.0], [99.4, 1557.0], [99.5, 1582.0], [99.6, 1642.0], [99.7, 1718.0], [99.8, 1796.0], [99.9, 1951.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
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
        data: {"result": {"minY": 1.0, "minX": 0.0, "maxY": 7895.0, "series": [{"data": [[0.0, 6.0], [600.0, 4846.0], [700.0, 7895.0], [800.0, 2063.0], [900.0, 936.0], [1000.0, 230.0], [1100.0, 89.0], [1200.0, 387.0], [1300.0, 194.0], [1400.0, 95.0], [1500.0, 57.0], [100.0, 764.0], [1600.0, 27.0], [1700.0, 25.0], [1800.0, 11.0], [1900.0, 10.0], [2000.0, 5.0], [2100.0, 4.0], [2200.0, 1.0], [2300.0, 1.0], [2400.0, 2.0], [2500.0, 1.0], [200.0, 205.0], [300.0, 52.0], [400.0, 34.0], [500.0, 65.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 2500.0, "title": "Response Time Distribution"}},
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
        data: {"result": {"minY": 144.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 16800.0, "series": [{"data": [[0.0, 1061.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 16800.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [[2.0, 144.0]], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 2.0, "title": "Synthetic Response Times Distribution"}},
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
        data: {"result": {"minY": 40.25395152792414, "minX": 1.75039122E12, "maxY": 45.0, "series": [{"data": [[1.75039134E12, 45.0], [1.75039146E12, 45.0], [1.75039128E12, 45.0], [1.7503914E12, 45.0], [1.75039122E12, 40.25395152792414], [1.75039152E12, 44.608885362951206]], "isOverall": false, "label": "线程组", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039152E12, "title": "Active Threads Over Time"}},
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
        data: {"result": {"minY": 175.0, "minX": 1.0, "maxY": 754.6, "series": [{"data": [[32.0, 621.0], [33.0, 618.3333333333333], [2.0, 715.0], [34.0, 657.3333333333334], [35.0, 649.8571428571429], [36.0, 682.0], [37.0, 698.6666666666666], [38.0, 708.5], [39.0, 709.0], [40.0, 729.8333333333333], [41.0, 726.0000000000001], [42.0, 752.8], [43.0, 716.4285714285714], [44.0, 754.6], [45.0, 747.9931263733132], [3.0, 296.5], [4.0, 243.25], [5.0, 225.2], [6.0, 175.0], [7.0, 277.5], [8.0, 240.66666666666669], [9.0, 228.85714285714283], [10.0, 384.2], [11.0, 251.0], [12.0, 322.8], [13.0, 379.2857142857143], [14.0, 328.83333333333337], [15.0, 325.8333333333333], [16.0, 367.33333333333337], [1.0, 681.0], [17.0, 389.83333333333337], [18.0, 407.0], [19.0, 394.5714285714286], [20.0, 423.5], [21.0, 416.0], [22.0, 382.8], [23.0, 474.12499999999994], [24.0, 463.8333333333333], [25.0, 468.83333333333337], [26.0, 439.14285714285717], [27.0, 508.7777777777778], [28.0, 515.4], [29.0, 527.6], [30.0, 587.0], [31.0, 566.6]], "isOverall": false, "label": "HTTP请求配置", "isController": false}, {"data": [[44.69508469869498, 744.1675090252742]], "isOverall": false, "label": "HTTP请求配置-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 45.0, "title": "Time VS Threads"}},
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
        data : {"result": {"minY": 3898.016666666667, "minX": 1.75039122E12, "maxY": 44121.25, "series": [{"data": [[1.75039134E12, 42464.5], [1.75039146E12, 41548.0], [1.75039128E12, 44121.25], [1.7503914E12, 42652.5], [1.75039122E12, 11150.75], [1.75039152E12, 29621.75]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.75039134E12, 14844.8], [1.75039146E12, 14524.3], [1.75039128E12, 15423.883333333333], [1.7503914E12, 14910.533333333333], [1.75039122E12, 3898.016666666667], [1.75039152E12, 10355.25]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039152E12, "title": "Bytes Throughput Over Time"}},
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
        data: {"result": {"minY": 717.3978695073233, "minX": 1.75039122E12, "maxY": 762.9061085972835, "series": [{"data": [[1.75039134E12, 748.0071942446033], [1.75039146E12, 762.9061085972835], [1.75039128E12, 717.3978695073233], [1.7503914E12, 744.3586776859491], [1.75039122E12, 758.7597471022135], [1.75039152E12, 746.4847282824263]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039152E12, "title": "Response Time Over Time"}},
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
        data: {"result": {"minY": 717.0607190412783, "minX": 1.75039122E12, "maxY": 762.4983031674218, "series": [{"data": [[1.75039134E12, 747.6959048146109], [1.75039146E12, 762.4983031674218], [1.75039128E12, 717.0607190412783], [1.7503914E12, 743.9181818181825], [1.75039122E12, 758.4826132771346], [1.75039152E12, 746.0329234430759]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039152E12, "title": "Latencies Over Time"}},
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
        data: {"result": {"minY": 0.0, "minX": 1.75039122E12, "maxY": 0.12644889357218156, "series": [{"data": [[1.75039134E12, 0.017708909795240733], [1.75039146E12, 0.014423076923076978], [1.75039128E12, 0.014913448735019966], [1.7503914E12, 0.01460055096418733], [1.75039122E12, 0.12644889357218156], [1.75039152E12, 0.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039152E12, "title": "Connect Time Over Time"}},
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
        data: {"result": {"minY": 86.0, "minX": 1.75039122E12, "maxY": 2537.0, "series": [{"data": [[1.75039134E12, 2537.0], [1.75039146E12, 2475.0], [1.75039128E12, 2025.0], [1.7503914E12, 2136.0], [1.75039122E12, 2193.0], [1.75039152E12, 2333.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.75039134E12, 935.0], [1.75039146E12, 941.0], [1.75039128E12, 833.4000000000001], [1.7503914E12, 882.9000000000001], [1.75039122E12, 973.0], [1.75039152E12, 896.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.75039134E12, 1562.0999999999995], [1.75039146E12, 1504.2600000000002], [1.75039128E12, 1346.88], [1.7503914E12, 1406.69], [1.75039122E12, 1366.0], [1.75039152E12, 1491.1400000000026]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.75039134E12, 1197.25], [1.75039146E12, 1190.8999999999978], [1.75039128E12, 984.3999999999996], [1.7503914E12, 1194.0], [1.75039122E12, 999.5], [1.75039152E12, 1214.8000000000002]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.75039134E12, 126.0], [1.75039146E12, 124.0], [1.75039128E12, 117.0], [1.7503914E12, 126.0], [1.75039122E12, 86.0], [1.75039152E12, 124.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.75039134E12, 723.0], [1.75039146E12, 743.0], [1.75039128E12, 710.0], [1.7503914E12, 734.0], [1.75039122E12, 801.0], [1.75039152E12, 727.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039152E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
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
    data: {"result": {"minY": 107.0, "minX": 25.0, "maxY": 1009.5, "series": [{"data": [[39.0, 873.0], [43.0, 949.0], [45.0, 759.0], [44.0, 940.0], [47.0, 904.0], [46.0, 1009.5], [48.0, 857.0], [49.0, 928.0], [51.0, 881.5], [50.0, 862.0], [52.0, 891.0], [53.0, 845.5], [55.0, 783.0], [54.0, 811.5], [57.0, 790.0], [56.0, 798.0], [58.0, 766.0], [59.0, 747.0], [60.0, 729.0], [61.0, 725.0], [62.0, 724.0], [63.0, 714.0], [64.0, 711.0], [65.0, 708.0], [66.0, 701.0], [67.0, 688.0], [68.0, 685.0], [71.0, 709.0], [70.0, 672.5], [69.0, 675.0], [25.0, 107.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 71.0, "title": "Response Time Vs Request"}},
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
    data: {"result": {"minY": 107.0, "minX": 25.0, "maxY": 1009.0, "series": [{"data": [[39.0, 873.0], [43.0, 948.0], [45.0, 759.0], [44.0, 939.5], [47.0, 904.0], [46.0, 1009.0], [48.0, 856.5], [49.0, 915.0], [51.0, 880.0], [50.0, 859.5], [52.0, 889.5], [53.0, 845.0], [55.0, 783.0], [54.0, 811.5], [57.0, 788.0], [56.0, 798.0], [58.0, 765.5], [59.0, 747.0], [60.0, 729.0], [61.0, 724.0], [62.0, 724.0], [63.0, 714.0], [64.0, 711.0], [65.0, 707.5], [66.0, 701.0], [67.0, 687.5], [68.0, 685.0], [71.0, 709.0], [70.0, 672.5], [69.0, 675.0], [25.0, 107.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 71.0, "title": "Latencies Vs Request"}},
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
        data: {"result": {"minY": 16.566666666666666, "minX": 1.75039122E12, "maxY": 62.583333333333336, "series": [{"data": [[1.75039134E12, 60.233333333333334], [1.75039146E12, 58.93333333333333], [1.75039128E12, 62.583333333333336], [1.7503914E12, 60.5], [1.75039122E12, 16.566666666666666], [1.75039152E12, 41.266666666666666]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039152E12, "title": "Hits Per Second"}},
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
        data: {"result": {"minY": 15.816666666666666, "minX": 1.75039122E12, "maxY": 62.583333333333336, "series": [{"data": [[1.75039134E12, 60.233333333333334], [1.75039146E12, 58.93333333333333], [1.75039128E12, 62.583333333333336], [1.7503914E12, 60.5], [1.75039122E12, 15.816666666666666], [1.75039152E12, 42.016666666666666]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039152E12, "title": "Codes Per Second"}},
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
        data: {"result": {"minY": 15.816666666666666, "minX": 1.75039122E12, "maxY": 62.583333333333336, "series": [{"data": [[1.75039134E12, 60.233333333333334], [1.75039146E12, 58.93333333333333], [1.75039128E12, 62.583333333333336], [1.7503914E12, 60.5], [1.75039122E12, 15.816666666666666], [1.75039152E12, 42.016666666666666]], "isOverall": false, "label": "HTTP请求配置-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039152E12, "title": "Transactions Per Second"}},
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
        data: {"result": {"minY": 15.816666666666666, "minX": 1.75039122E12, "maxY": 62.583333333333336, "series": [{"data": [[1.75039134E12, 60.233333333333334], [1.75039146E12, 58.93333333333333], [1.75039128E12, 62.583333333333336], [1.7503914E12, 60.5], [1.75039122E12, 15.816666666666666], [1.75039152E12, 42.016666666666666]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039152E12, "title": "Total Transactions Per Second"}},
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

