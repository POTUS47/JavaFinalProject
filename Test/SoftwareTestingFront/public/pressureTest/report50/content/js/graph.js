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
        data: {"result": {"minY": 88.0, "minX": 0.0, "maxY": 3115.0, "series": [{"data": [[0.0, 88.0], [0.1, 116.0], [0.2, 123.0], [0.3, 126.0], [0.4, 128.0], [0.5, 131.0], [0.6, 134.0], [0.7, 136.0], [0.8, 137.0], [0.9, 138.0], [1.0, 139.0], [1.1, 141.0], [1.2, 142.0], [1.3, 143.0], [1.4, 145.0], [1.5, 146.0], [1.6, 147.0], [1.7, 148.0], [1.8, 150.0], [1.9, 150.0], [2.0, 152.0], [2.1, 153.0], [2.2, 155.0], [2.3, 156.0], [2.4, 158.0], [2.5, 160.0], [2.6, 162.0], [2.7, 164.0], [2.8, 165.0], [2.9, 167.0], [3.0, 169.0], [3.1, 171.0], [3.2, 173.0], [3.3, 176.0], [3.4, 180.0], [3.5, 183.0], [3.6, 187.0], [3.7, 191.0], [3.8, 195.0], [3.9, 199.0], [4.0, 204.0], [4.1, 209.0], [4.2, 215.0], [4.3, 223.0], [4.4, 227.0], [4.5, 243.0], [4.6, 270.0], [4.7, 304.0], [4.8, 348.0], [4.9, 419.0], [5.0, 502.0], [5.1, 576.0], [5.2, 615.0], [5.3, 626.0], [5.4, 639.0], [5.5, 644.0], [5.6, 648.0], [5.7, 651.0], [5.8, 653.0], [5.9, 655.0], [6.0, 658.0], [6.1, 659.0], [6.2, 661.0], [6.3, 663.0], [6.4, 666.0], [6.5, 667.0], [6.6, 669.0], [6.7, 671.0], [6.8, 673.0], [6.9, 675.0], [7.0, 677.0], [7.1, 678.0], [7.2, 680.0], [7.3, 681.0], [7.4, 682.0], [7.5, 684.0], [7.6, 685.0], [7.7, 686.0], [7.8, 688.0], [7.9, 690.0], [8.0, 691.0], [8.1, 692.0], [8.2, 692.0], [8.3, 694.0], [8.4, 694.0], [8.5, 695.0], [8.6, 697.0], [8.7, 697.0], [8.8, 698.0], [8.9, 699.0], [9.0, 700.0], [9.1, 701.0], [9.2, 702.0], [9.3, 703.0], [9.4, 704.0], [9.5, 705.0], [9.6, 706.0], [9.7, 707.0], [9.8, 707.0], [9.9, 708.0], [10.0, 709.0], [10.1, 710.0], [10.2, 710.0], [10.3, 711.0], [10.4, 712.0], [10.5, 713.0], [10.6, 714.0], [10.7, 715.0], [10.8, 715.0], [10.9, 716.0], [11.0, 716.0], [11.1, 717.0], [11.2, 717.0], [11.3, 718.0], [11.4, 718.0], [11.5, 719.0], [11.6, 719.0], [11.7, 720.0], [11.8, 720.0], [11.9, 721.0], [12.0, 721.0], [12.1, 722.0], [12.2, 722.0], [12.3, 723.0], [12.4, 723.0], [12.5, 723.0], [12.6, 724.0], [12.7, 724.0], [12.8, 725.0], [12.9, 725.0], [13.0, 725.0], [13.1, 726.0], [13.2, 726.0], [13.3, 726.0], [13.4, 727.0], [13.5, 727.0], [13.6, 728.0], [13.7, 728.0], [13.8, 729.0], [13.9, 729.0], [14.0, 730.0], [14.1, 730.0], [14.2, 730.0], [14.3, 731.0], [14.4, 731.0], [14.5, 731.0], [14.6, 732.0], [14.7, 732.0], [14.8, 732.0], [14.9, 733.0], [15.0, 733.0], [15.1, 733.0], [15.2, 734.0], [15.3, 734.0], [15.4, 734.0], [15.5, 735.0], [15.6, 735.0], [15.7, 735.0], [15.8, 736.0], [15.9, 736.0], [16.0, 736.0], [16.1, 737.0], [16.2, 737.0], [16.3, 737.0], [16.4, 737.0], [16.5, 738.0], [16.6, 738.0], [16.7, 738.0], [16.8, 738.0], [16.9, 739.0], [17.0, 739.0], [17.1, 739.0], [17.2, 739.0], [17.3, 739.0], [17.4, 740.0], [17.5, 740.0], [17.6, 740.0], [17.7, 741.0], [17.8, 741.0], [17.9, 741.0], [18.0, 741.0], [18.1, 741.0], [18.2, 742.0], [18.3, 742.0], [18.4, 742.0], [18.5, 743.0], [18.6, 743.0], [18.7, 743.0], [18.8, 743.0], [18.9, 743.0], [19.0, 744.0], [19.1, 744.0], [19.2, 744.0], [19.3, 745.0], [19.4, 745.0], [19.5, 745.0], [19.6, 745.0], [19.7, 746.0], [19.8, 746.0], [19.9, 746.0], [20.0, 746.0], [20.1, 747.0], [20.2, 747.0], [20.3, 747.0], [20.4, 747.0], [20.5, 747.0], [20.6, 748.0], [20.7, 748.0], [20.8, 748.0], [20.9, 748.0], [21.0, 748.0], [21.1, 748.0], [21.2, 749.0], [21.3, 749.0], [21.4, 749.0], [21.5, 749.0], [21.6, 750.0], [21.7, 750.0], [21.8, 750.0], [21.9, 750.0], [22.0, 750.0], [22.1, 751.0], [22.2, 751.0], [22.3, 751.0], [22.4, 751.0], [22.5, 751.0], [22.6, 752.0], [22.7, 752.0], [22.8, 752.0], [22.9, 752.0], [23.0, 752.0], [23.1, 753.0], [23.2, 753.0], [23.3, 753.0], [23.4, 753.0], [23.5, 753.0], [23.6, 754.0], [23.7, 754.0], [23.8, 754.0], [23.9, 754.0], [24.0, 754.0], [24.1, 755.0], [24.2, 755.0], [24.3, 755.0], [24.4, 755.0], [24.5, 755.0], [24.6, 756.0], [24.7, 756.0], [24.8, 756.0], [24.9, 756.0], [25.0, 756.0], [25.1, 757.0], [25.2, 757.0], [25.3, 757.0], [25.4, 757.0], [25.5, 757.0], [25.6, 757.0], [25.7, 757.0], [25.8, 758.0], [25.9, 758.0], [26.0, 758.0], [26.1, 758.0], [26.2, 758.0], [26.3, 758.0], [26.4, 759.0], [26.5, 759.0], [26.6, 759.0], [26.7, 759.0], [26.8, 759.0], [26.9, 759.0], [27.0, 760.0], [27.1, 760.0], [27.2, 760.0], [27.3, 760.0], [27.4, 760.0], [27.5, 761.0], [27.6, 761.0], [27.7, 761.0], [27.8, 761.0], [27.9, 761.0], [28.0, 761.0], [28.1, 761.0], [28.2, 762.0], [28.3, 762.0], [28.4, 762.0], [28.5, 762.0], [28.6, 762.0], [28.7, 762.0], [28.8, 763.0], [28.9, 763.0], [29.0, 763.0], [29.1, 763.0], [29.2, 763.0], [29.3, 763.0], [29.4, 764.0], [29.5, 764.0], [29.6, 764.0], [29.7, 764.0], [29.8, 764.0], [29.9, 765.0], [30.0, 765.0], [30.1, 765.0], [30.2, 765.0], [30.3, 765.0], [30.4, 765.0], [30.5, 765.0], [30.6, 766.0], [30.7, 766.0], [30.8, 766.0], [30.9, 766.0], [31.0, 766.0], [31.1, 766.0], [31.2, 767.0], [31.3, 767.0], [31.4, 767.0], [31.5, 767.0], [31.6, 767.0], [31.7, 767.0], [31.8, 768.0], [31.9, 768.0], [32.0, 768.0], [32.1, 768.0], [32.2, 768.0], [32.3, 768.0], [32.4, 769.0], [32.5, 769.0], [32.6, 769.0], [32.7, 769.0], [32.8, 769.0], [32.9, 769.0], [33.0, 769.0], [33.1, 770.0], [33.2, 770.0], [33.3, 770.0], [33.4, 770.0], [33.5, 770.0], [33.6, 770.0], [33.7, 770.0], [33.8, 770.0], [33.9, 771.0], [34.0, 771.0], [34.1, 771.0], [34.2, 771.0], [34.3, 771.0], [34.4, 771.0], [34.5, 771.0], [34.6, 772.0], [34.7, 772.0], [34.8, 772.0], [34.9, 772.0], [35.0, 772.0], [35.1, 772.0], [35.2, 772.0], [35.3, 773.0], [35.4, 773.0], [35.5, 773.0], [35.6, 773.0], [35.7, 773.0], [35.8, 773.0], [35.9, 773.0], [36.0, 774.0], [36.1, 774.0], [36.2, 774.0], [36.3, 774.0], [36.4, 774.0], [36.5, 774.0], [36.6, 774.0], [36.7, 775.0], [36.8, 775.0], [36.9, 775.0], [37.0, 775.0], [37.1, 775.0], [37.2, 775.0], [37.3, 776.0], [37.4, 776.0], [37.5, 776.0], [37.6, 776.0], [37.7, 776.0], [37.8, 776.0], [37.9, 776.0], [38.0, 776.0], [38.1, 777.0], [38.2, 777.0], [38.3, 777.0], [38.4, 777.0], [38.5, 777.0], [38.6, 777.0], [38.7, 777.0], [38.8, 778.0], [38.9, 778.0], [39.0, 778.0], [39.1, 778.0], [39.2, 778.0], [39.3, 778.0], [39.4, 778.0], [39.5, 779.0], [39.6, 779.0], [39.7, 779.0], [39.8, 779.0], [39.9, 779.0], [40.0, 779.0], [40.1, 779.0], [40.2, 779.0], [40.3, 780.0], [40.4, 780.0], [40.5, 780.0], [40.6, 780.0], [40.7, 780.0], [40.8, 780.0], [40.9, 781.0], [41.0, 781.0], [41.1, 781.0], [41.2, 781.0], [41.3, 781.0], [41.4, 781.0], [41.5, 781.0], [41.6, 781.0], [41.7, 781.0], [41.8, 782.0], [41.9, 782.0], [42.0, 782.0], [42.1, 782.0], [42.2, 782.0], [42.3, 783.0], [42.4, 783.0], [42.5, 783.0], [42.6, 783.0], [42.7, 783.0], [42.8, 783.0], [42.9, 783.0], [43.0, 784.0], [43.1, 784.0], [43.2, 784.0], [43.3, 784.0], [43.4, 784.0], [43.5, 784.0], [43.6, 784.0], [43.7, 785.0], [43.8, 785.0], [43.9, 785.0], [44.0, 785.0], [44.1, 785.0], [44.2, 785.0], [44.3, 785.0], [44.4, 786.0], [44.5, 786.0], [44.6, 786.0], [44.7, 786.0], [44.8, 786.0], [44.9, 786.0], [45.0, 786.0], [45.1, 787.0], [45.2, 787.0], [45.3, 787.0], [45.4, 787.0], [45.5, 787.0], [45.6, 787.0], [45.7, 787.0], [45.8, 788.0], [45.9, 788.0], [46.0, 788.0], [46.1, 788.0], [46.2, 788.0], [46.3, 788.0], [46.4, 788.0], [46.5, 789.0], [46.6, 789.0], [46.7, 789.0], [46.8, 789.0], [46.9, 789.0], [47.0, 789.0], [47.1, 789.0], [47.2, 790.0], [47.3, 790.0], [47.4, 790.0], [47.5, 790.0], [47.6, 790.0], [47.7, 790.0], [47.8, 791.0], [47.9, 791.0], [48.0, 791.0], [48.1, 791.0], [48.2, 791.0], [48.3, 791.0], [48.4, 791.0], [48.5, 791.0], [48.6, 792.0], [48.7, 792.0], [48.8, 792.0], [48.9, 792.0], [49.0, 792.0], [49.1, 792.0], [49.2, 792.0], [49.3, 793.0], [49.4, 793.0], [49.5, 793.0], [49.6, 793.0], [49.7, 793.0], [49.8, 793.0], [49.9, 794.0], [50.0, 794.0], [50.1, 794.0], [50.2, 794.0], [50.3, 794.0], [50.4, 794.0], [50.5, 795.0], [50.6, 795.0], [50.7, 795.0], [50.8, 795.0], [50.9, 795.0], [51.0, 795.0], [51.1, 796.0], [51.2, 796.0], [51.3, 796.0], [51.4, 796.0], [51.5, 796.0], [51.6, 796.0], [51.7, 797.0], [51.8, 797.0], [51.9, 797.0], [52.0, 797.0], [52.1, 797.0], [52.2, 797.0], [52.3, 798.0], [52.4, 798.0], [52.5, 798.0], [52.6, 798.0], [52.7, 798.0], [52.8, 799.0], [52.9, 799.0], [53.0, 799.0], [53.1, 799.0], [53.2, 799.0], [53.3, 799.0], [53.4, 799.0], [53.5, 799.0], [53.6, 800.0], [53.7, 800.0], [53.8, 800.0], [53.9, 800.0], [54.0, 800.0], [54.1, 801.0], [54.2, 801.0], [54.3, 801.0], [54.4, 801.0], [54.5, 801.0], [54.6, 801.0], [54.7, 802.0], [54.8, 802.0], [54.9, 802.0], [55.0, 802.0], [55.1, 802.0], [55.2, 802.0], [55.3, 802.0], [55.4, 803.0], [55.5, 803.0], [55.6, 803.0], [55.7, 803.0], [55.8, 803.0], [55.9, 803.0], [56.0, 804.0], [56.1, 804.0], [56.2, 804.0], [56.3, 804.0], [56.4, 804.0], [56.5, 804.0], [56.6, 805.0], [56.7, 805.0], [56.8, 805.0], [56.9, 805.0], [57.0, 805.0], [57.1, 806.0], [57.2, 806.0], [57.3, 806.0], [57.4, 806.0], [57.5, 806.0], [57.6, 806.0], [57.7, 807.0], [57.8, 807.0], [57.9, 807.0], [58.0, 807.0], [58.1, 807.0], [58.2, 808.0], [58.3, 808.0], [58.4, 808.0], [58.5, 808.0], [58.6, 808.0], [58.7, 809.0], [58.8, 809.0], [58.9, 809.0], [59.0, 809.0], [59.1, 809.0], [59.2, 809.0], [59.3, 810.0], [59.4, 810.0], [59.5, 810.0], [59.6, 810.0], [59.7, 810.0], [59.8, 810.0], [59.9, 811.0], [60.0, 811.0], [60.1, 811.0], [60.2, 811.0], [60.3, 811.0], [60.4, 811.0], [60.5, 812.0], [60.6, 812.0], [60.7, 812.0], [60.8, 812.0], [60.9, 812.0], [61.0, 813.0], [61.1, 813.0], [61.2, 813.0], [61.3, 813.0], [61.4, 813.0], [61.5, 813.0], [61.6, 814.0], [61.7, 814.0], [61.8, 814.0], [61.9, 814.0], [62.0, 814.0], [62.1, 815.0], [62.2, 815.0], [62.3, 815.0], [62.4, 815.0], [62.5, 816.0], [62.6, 816.0], [62.7, 816.0], [62.8, 816.0], [62.9, 816.0], [63.0, 817.0], [63.1, 817.0], [63.2, 817.0], [63.3, 817.0], [63.4, 817.0], [63.5, 818.0], [63.6, 818.0], [63.7, 818.0], [63.8, 818.0], [63.9, 818.0], [64.0, 818.0], [64.1, 819.0], [64.2, 819.0], [64.3, 819.0], [64.4, 819.0], [64.5, 819.0], [64.6, 820.0], [64.7, 820.0], [64.8, 820.0], [64.9, 820.0], [65.0, 820.0], [65.1, 821.0], [65.2, 821.0], [65.3, 821.0], [65.4, 821.0], [65.5, 821.0], [65.6, 822.0], [65.7, 822.0], [65.8, 822.0], [65.9, 822.0], [66.0, 822.0], [66.1, 823.0], [66.2, 823.0], [66.3, 823.0], [66.4, 823.0], [66.5, 823.0], [66.6, 824.0], [66.7, 824.0], [66.8, 824.0], [66.9, 824.0], [67.0, 824.0], [67.1, 825.0], [67.2, 825.0], [67.3, 825.0], [67.4, 825.0], [67.5, 826.0], [67.6, 826.0], [67.7, 826.0], [67.8, 826.0], [67.9, 826.0], [68.0, 827.0], [68.1, 827.0], [68.2, 827.0], [68.3, 828.0], [68.4, 828.0], [68.5, 828.0], [68.6, 828.0], [68.7, 828.0], [68.8, 829.0], [68.9, 829.0], [69.0, 829.0], [69.1, 829.0], [69.2, 830.0], [69.3, 830.0], [69.4, 830.0], [69.5, 830.0], [69.6, 831.0], [69.7, 831.0], [69.8, 831.0], [69.9, 831.0], [70.0, 832.0], [70.1, 832.0], [70.2, 832.0], [70.3, 832.0], [70.4, 833.0], [70.5, 833.0], [70.6, 833.0], [70.7, 833.0], [70.8, 834.0], [70.9, 834.0], [71.0, 834.0], [71.1, 834.0], [71.2, 834.0], [71.3, 835.0], [71.4, 835.0], [71.5, 835.0], [71.6, 835.0], [71.7, 836.0], [71.8, 836.0], [71.9, 836.0], [72.0, 837.0], [72.1, 837.0], [72.2, 837.0], [72.3, 837.0], [72.4, 838.0], [72.5, 838.0], [72.6, 838.0], [72.7, 839.0], [72.8, 839.0], [72.9, 839.0], [73.0, 839.0], [73.1, 840.0], [73.2, 840.0], [73.3, 840.0], [73.4, 841.0], [73.5, 841.0], [73.6, 841.0], [73.7, 841.0], [73.8, 842.0], [73.9, 842.0], [74.0, 842.0], [74.1, 843.0], [74.2, 843.0], [74.3, 843.0], [74.4, 844.0], [74.5, 844.0], [74.6, 844.0], [74.7, 845.0], [74.8, 845.0], [74.9, 845.0], [75.0, 846.0], [75.1, 846.0], [75.2, 846.0], [75.3, 847.0], [75.4, 847.0], [75.5, 847.0], [75.6, 847.0], [75.7, 848.0], [75.8, 848.0], [75.9, 849.0], [76.0, 849.0], [76.1, 849.0], [76.2, 850.0], [76.3, 850.0], [76.4, 850.0], [76.5, 851.0], [76.6, 851.0], [76.7, 852.0], [76.8, 852.0], [76.9, 852.0], [77.0, 853.0], [77.1, 853.0], [77.2, 853.0], [77.3, 854.0], [77.4, 854.0], [77.5, 854.0], [77.6, 855.0], [77.7, 855.0], [77.8, 856.0], [77.9, 856.0], [78.0, 856.0], [78.1, 857.0], [78.2, 857.0], [78.3, 857.0], [78.4, 858.0], [78.5, 858.0], [78.6, 858.0], [78.7, 858.0], [78.8, 859.0], [78.9, 859.0], [79.0, 860.0], [79.1, 860.0], [79.2, 860.0], [79.3, 861.0], [79.4, 861.0], [79.5, 861.0], [79.6, 862.0], [79.7, 862.0], [79.8, 863.0], [79.9, 863.0], [80.0, 864.0], [80.1, 864.0], [80.2, 865.0], [80.3, 865.0], [80.4, 866.0], [80.5, 866.0], [80.6, 866.0], [80.7, 867.0], [80.8, 867.0], [80.9, 868.0], [81.0, 868.0], [81.1, 868.0], [81.2, 869.0], [81.3, 869.0], [81.4, 870.0], [81.5, 870.0], [81.6, 870.0], [81.7, 871.0], [81.8, 871.0], [81.9, 871.0], [82.0, 872.0], [82.1, 872.0], [82.2, 873.0], [82.3, 873.0], [82.4, 874.0], [82.5, 874.0], [82.6, 875.0], [82.7, 875.0], [82.8, 876.0], [82.9, 876.0], [83.0, 877.0], [83.1, 877.0], [83.2, 878.0], [83.3, 878.0], [83.4, 878.0], [83.5, 879.0], [83.6, 880.0], [83.7, 880.0], [83.8, 881.0], [83.9, 881.0], [84.0, 882.0], [84.1, 882.0], [84.2, 883.0], [84.3, 883.0], [84.4, 884.0], [84.5, 885.0], [84.6, 885.0], [84.7, 886.0], [84.8, 886.0], [84.9, 887.0], [85.0, 887.0], [85.1, 888.0], [85.2, 888.0], [85.3, 889.0], [85.4, 889.0], [85.5, 890.0], [85.6, 890.0], [85.7, 891.0], [85.8, 892.0], [85.9, 893.0], [86.0, 893.0], [86.1, 894.0], [86.2, 894.0], [86.3, 895.0], [86.4, 896.0], [86.5, 896.0], [86.6, 897.0], [86.7, 898.0], [86.8, 898.0], [86.9, 899.0], [87.0, 899.0], [87.1, 900.0], [87.2, 901.0], [87.3, 901.0], [87.4, 902.0], [87.5, 903.0], [87.6, 903.0], [87.7, 904.0], [87.8, 905.0], [87.9, 905.0], [88.0, 906.0], [88.1, 907.0], [88.2, 908.0], [88.3, 909.0], [88.4, 910.0], [88.5, 911.0], [88.6, 912.0], [88.7, 913.0], [88.8, 914.0], [88.9, 915.0], [89.0, 916.0], [89.1, 917.0], [89.2, 918.0], [89.3, 919.0], [89.4, 920.0], [89.5, 921.0], [89.6, 922.0], [89.7, 923.0], [89.8, 924.0], [89.9, 925.0], [90.0, 927.0], [90.1, 928.0], [90.2, 929.0], [90.3, 930.0], [90.4, 931.0], [90.5, 932.0], [90.6, 934.0], [90.7, 935.0], [90.8, 937.0], [90.9, 938.0], [91.0, 940.0], [91.1, 941.0], [91.2, 943.0], [91.3, 944.0], [91.4, 945.0], [91.5, 947.0], [91.6, 950.0], [91.7, 952.0], [91.8, 954.0], [91.9, 956.0], [92.0, 958.0], [92.1, 962.0], [92.2, 965.0], [92.3, 968.0], [92.4, 970.0], [92.5, 973.0], [92.6, 976.0], [92.7, 980.0], [92.8, 983.0], [92.9, 985.0], [93.0, 987.0], [93.1, 992.0], [93.2, 994.0], [93.3, 998.0], [93.4, 1001.0], [93.5, 1005.0], [93.6, 1009.0], [93.7, 1013.0], [93.8, 1017.0], [93.9, 1021.0], [94.0, 1026.0], [94.1, 1032.0], [94.2, 1037.0], [94.3, 1043.0], [94.4, 1050.0], [94.5, 1055.0], [94.6, 1064.0], [94.7, 1070.0], [94.8, 1075.0], [94.9, 1081.0], [95.0, 1087.0], [95.1, 1096.0], [95.2, 1107.0], [95.3, 1117.0], [95.4, 1137.0], [95.5, 1151.0], [95.6, 1171.0], [95.7, 1200.0], [95.8, 1227.0], [95.9, 1257.0], [96.0, 1274.0], [96.1, 1296.0], [96.2, 1323.0], [96.3, 1337.0], [96.4, 1349.0], [96.5, 1358.0], [96.6, 1366.0], [96.7, 1373.0], [96.8, 1379.0], [96.9, 1385.0], [97.0, 1389.0], [97.1, 1396.0], [97.2, 1400.0], [97.3, 1404.0], [97.4, 1411.0], [97.5, 1416.0], [97.6, 1421.0], [97.7, 1426.0], [97.8, 1433.0], [97.9, 1438.0], [98.0, 1443.0], [98.1, 1447.0], [98.2, 1452.0], [98.3, 1457.0], [98.4, 1465.0], [98.5, 1468.0], [98.6, 1476.0], [98.7, 1483.0], [98.8, 1495.0], [98.9, 1507.0], [99.0, 1515.0], [99.1, 1524.0], [99.2, 1533.0], [99.3, 1550.0], [99.4, 1571.0], [99.5, 1605.0], [99.6, 1624.0], [99.7, 1704.0], [99.8, 1823.0], [99.9, 2043.0], [100.0, 3115.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
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
        data: {"result": {"minY": 1.0, "minX": 0.0, "maxY": 8269.0, "series": [{"data": [[0.0, 7.0], [600.0, 698.0], [700.0, 8269.0], [800.0, 6206.0], [900.0, 1176.0], [1000.0, 333.0], [1100.0, 102.0], [1200.0, 78.0], [1300.0, 200.0], [1400.0, 306.0], [1500.0, 119.0], [100.0, 718.0], [1600.0, 39.0], [1700.0, 14.0], [1800.0, 14.0], [1900.0, 8.0], [2000.0, 10.0], [2100.0, 5.0], [2300.0, 1.0], [2200.0, 2.0], [2400.0, 1.0], [3100.0, 1.0], [200.0, 142.0], [300.0, 38.0], [400.0, 20.0], [500.0, 29.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 3100.0, "title": "Response Time Distribution"}},
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
        data: {"result": {"minY": 210.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 17400.0, "series": [{"data": [[0.0, 926.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 17400.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [[2.0, 210.0]], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 2.0, "title": "Synthetic Response Times Distribution"}},
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
        data: {"result": {"minY": 41.79166666666667, "minX": 1.75039164E12, "maxY": 50.0, "series": [{"data": [[1.75039182E12, 50.0], [1.75039164E12, 41.79166666666667], [1.75039194E12, 49.59556144418682], [1.75039176E12, 50.0], [1.75039188E12, 50.0], [1.7503917E12, 50.0]], "isOverall": false, "label": "线程组", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039194E12, "title": "Active Threads Over Time"}},
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
        data: {"result": {"minY": 92.66666666666667, "minX": 1.0, "maxY": 859.1666666666667, "series": [{"data": [[2.0, 738.0], [3.0, 334.33333333333337], [4.0, 92.66666666666667], [5.0, 362.5], [6.0, 237.8], [7.0, 396.6], [8.0, 264.2857142857143], [9.0, 276.6], [10.0, 330.0], [11.0, 316.4], [12.0, 311.66666666666663], [13.0, 321.0], [14.0, 326.0], [15.0, 337.5], [16.0, 355.16666666666663], [17.0, 367.0], [18.0, 382.5], [19.0, 402.66666666666663], [20.0, 411.0], [21.0, 453.6], [22.0, 437.14285714285717], [23.0, 487.25], [24.0, 486.0], [25.0, 534.0], [26.0, 520.2857142857142], [27.0, 543.8333333333334], [28.0, 570.8], [29.0, 550.2], [30.0, 643.6666666666666], [31.0, 573.5], [32.0, 643.7142857142857], [33.0, 628.4], [34.0, 640.1666666666667], [35.0, 636.3333333333334], [36.0, 660.0], [37.0, 678.2], [38.0, 676.8571428571429], [39.0, 683.4], [40.0, 697.2], [41.0, 726.8333333333334], [42.0, 859.1666666666667], [43.0, 763.8333333333333], [44.0, 773.2], [45.0, 798.3333333333334], [46.0, 791.5714285714286], [47.0, 708.8333333333334], [48.0, 841.8333333333334], [49.0, 836.625], [50.0, 807.139816132209], [1.0, 756.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}, {"data": [[49.66842900302115, 803.3064307293904]], "isOverall": false, "label": "HTTP请求配置-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 50.0, "title": "Time VS Threads"}},
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
        data : {"result": {"minY": 2464.4, "minX": 1.75039164E12, "maxY": 45531.25, "series": [{"data": [[1.75039182E12, 42488.0], [1.75039164E12, 7050.0], [1.75039194E12, 35473.25], [1.75039176E12, 43263.5], [1.75039188E12, 43992.0], [1.7503917E12, 45531.25]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.75039182E12, 14853.066666666668], [1.75039164E12, 2464.4], [1.75039194E12, 12400.85], [1.75039176E12, 15124.1], [1.75039188E12, 15378.6], [1.7503917E12, 15916.916666666666]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039194E12, "title": "Bytes Throughput Over Time"}},
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
        data: {"result": {"minY": 775.1550967741935, "minX": 1.75039164E12, "maxY": 830.9009955752223, "series": [{"data": [[1.75039182E12, 830.9009955752223], [1.75039164E12, 786.6116666666661], [1.75039194E12, 800.8363696588287], [1.75039176E12, 813.0559478544272], [1.75039188E12, 800.8707264957253], [1.7503917E12, 775.1550967741935]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039194E12, "title": "Response Time Over Time"}},
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
        data: {"result": {"minY": 774.8358709677416, "minX": 1.75039164E12, "maxY": 830.6078539823022, "series": [{"data": [[1.75039182E12, 830.6078539823022], [1.75039164E12, 786.2550000000001], [1.75039194E12, 800.5044716793633], [1.75039176E12, 812.7547528517107], [1.75039188E12, 800.5625000000014], [1.7503917E12, 774.8358709677416]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039194E12, "title": "Latencies Over Time"}},
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
        data: {"result": {"minY": 0.0, "minX": 1.75039164E12, "maxY": 0.1883333333333335, "series": [{"data": [[1.75039182E12, 0.017975663716814177], [1.75039164E12, 0.1883333333333335], [1.75039194E12, 0.0], [1.75039176E12, 0.02009777294948397], [1.75039188E12, 0.01709401709401708], [1.7503917E12, 0.015999999999999973]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039194E12, "title": "Connect Time Over Time"}},
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
        data: {"result": {"minY": 88.0, "minX": 1.75039164E12, "maxY": 3115.0, "series": [{"data": [[1.75039182E12, 2280.0], [1.75039164E12, 1874.0], [1.75039194E12, 2218.0], [1.75039176E12, 3115.0], [1.75039188E12, 2161.0], [1.7503917E12, 2418.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.75039182E12, 918.0], [1.75039164E12, 1025.6999999999998], [1.75039194E12, 877.0], [1.75039176E12, 980.0], [1.75039188E12, 876.0], [1.7503917E12, 957.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.75039182E12, 1526.1499999999996], [1.75039164E12, 1160.6600000000003], [1.75039194E12, 1480.8000000000002], [1.75039176E12, 1592.17], [1.75039188E12, 1488.5500000000002], [1.7503917E12, 1517.159999999998]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.75039182E12, 962.2999999999993], [1.75039164E12, 1066.9499999999998], [1.75039194E12, 1004.0], [1.75039176E12, 1311.5499999999997], [1.75039188E12, 957.5], [1.7503917E12, 1172.5999999999967]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.75039182E12, 123.0], [1.75039164E12, 88.0], [1.75039194E12, 122.0], [1.75039176E12, 121.0], [1.75039188E12, 123.0], [1.7503917E12, 111.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.75039182E12, 826.0], [1.75039164E12, 910.0], [1.75039194E12, 793.0], [1.75039176E12, 794.0], [1.75039188E12, 791.5], [1.7503917E12, 758.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039194E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
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
    data: {"result": {"minY": 96.0, "minX": 3.0, "maxY": 1037.5, "series": [{"data": [[45.0, 396.0], [44.0, 1012.5], [47.0, 749.5], [46.0, 1037.5], [3.0, 96.0], [49.0, 939.0], [48.0, 943.0], [51.0, 911.0], [50.0, 974.0], [53.0, 943.5], [52.0, 909.0], [54.0, 902.5], [55.0, 883.5], [56.0, 886.5], [57.0, 861.5], [58.0, 834.0], [59.0, 823.0], [60.0, 805.0], [61.0, 805.0], [62.0, 795.0], [63.0, 791.0], [64.0, 783.0], [65.0, 777.0], [67.0, 775.0], [66.0, 787.0], [71.0, 692.5], [69.0, 733.5], [70.0, 721.0], [68.0, 750.5], [72.0, 697.0], [75.0, 686.5], [73.0, 689.5], [21.0, 758.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 75.0, "title": "Response Time Vs Request"}},
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
    data: {"result": {"minY": 96.0, "minX": 3.0, "maxY": 1037.0, "series": [{"data": [[45.0, 396.0], [44.0, 1012.0], [47.0, 749.0], [46.0, 1037.0], [3.0, 96.0], [49.0, 939.0], [48.0, 942.5], [51.0, 911.0], [50.0, 974.0], [53.0, 941.5], [52.0, 909.0], [54.0, 902.5], [55.0, 883.0], [56.0, 886.0], [57.0, 861.0], [58.0, 834.0], [59.0, 823.0], [60.0, 804.0], [61.0, 804.5], [62.0, 794.0], [63.0, 791.0], [64.0, 783.0], [65.0, 777.0], [67.0, 775.0], [66.0, 787.0], [71.0, 692.0], [69.0, 733.0], [70.0, 721.0], [68.0, 750.0], [72.0, 697.0], [75.0, 686.0], [73.0, 689.5], [21.0, 757.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 75.0, "title": "Latencies Vs Request"}},
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
        data: {"result": {"minY": 10.833333333333334, "minX": 1.75039164E12, "maxY": 64.58333333333333, "series": [{"data": [[1.75039182E12, 60.266666666666666], [1.75039164E12, 10.833333333333334], [1.75039194E12, 49.483333333333334], [1.75039176E12, 61.36666666666667], [1.75039188E12, 62.4], [1.7503917E12, 64.58333333333333]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039194E12, "title": "Hits Per Second"}},
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
        data: {"result": {"minY": 10.0, "minX": 1.75039164E12, "maxY": 64.58333333333333, "series": [{"data": [[1.75039182E12, 60.266666666666666], [1.75039164E12, 10.0], [1.75039194E12, 50.31666666666667], [1.75039176E12, 61.36666666666667], [1.75039188E12, 62.4], [1.7503917E12, 64.58333333333333]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039194E12, "title": "Codes Per Second"}},
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
        data: {"result": {"minY": 10.0, "minX": 1.75039164E12, "maxY": 64.58333333333333, "series": [{"data": [[1.75039182E12, 60.266666666666666], [1.75039164E12, 10.0], [1.75039194E12, 50.31666666666667], [1.75039176E12, 61.36666666666667], [1.75039188E12, 62.4], [1.7503917E12, 64.58333333333333]], "isOverall": false, "label": "HTTP请求配置-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039194E12, "title": "Transactions Per Second"}},
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
        data: {"result": {"minY": 10.0, "minX": 1.75039164E12, "maxY": 64.58333333333333, "series": [{"data": [[1.75039182E12, 60.266666666666666], [1.75039164E12, 10.0], [1.75039194E12, 50.31666666666667], [1.75039176E12, 61.36666666666667], [1.75039188E12, 62.4], [1.7503917E12, 64.58333333333333]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039194E12, "title": "Total Transactions Per Second"}},
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

