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
        data: {"result": {"minY": 121.0, "minX": 0.0, "maxY": 2648.0, "series": [{"data": [[0.0, 121.0], [0.1, 156.0], [0.2, 185.0], [0.3, 231.0], [0.4, 311.0], [0.5, 379.0], [0.6, 404.0], [0.7, 427.0], [0.8, 455.0], [0.9, 472.0], [1.0, 478.0], [1.1, 482.0], [1.2, 484.0], [1.3, 486.0], [1.4, 488.0], [1.5, 490.0], [1.6, 491.0], [1.7, 492.0], [1.8, 493.0], [1.9, 494.0], [2.0, 496.0], [2.1, 497.0], [2.2, 498.0], [2.3, 499.0], [2.4, 500.0], [2.5, 501.0], [2.6, 501.0], [2.7, 502.0], [2.8, 503.0], [2.9, 503.0], [3.0, 504.0], [3.1, 504.0], [3.2, 505.0], [3.3, 505.0], [3.4, 506.0], [3.5, 506.0], [3.6, 507.0], [3.7, 508.0], [3.8, 508.0], [3.9, 508.0], [4.0, 509.0], [4.1, 509.0], [4.2, 509.0], [4.3, 510.0], [4.4, 510.0], [4.5, 511.0], [4.6, 511.0], [4.7, 512.0], [4.8, 512.0], [4.9, 512.0], [5.0, 513.0], [5.1, 513.0], [5.2, 513.0], [5.3, 514.0], [5.4, 514.0], [5.5, 514.0], [5.6, 515.0], [5.7, 515.0], [5.8, 515.0], [5.9, 515.0], [6.0, 516.0], [6.1, 516.0], [6.2, 516.0], [6.3, 516.0], [6.4, 517.0], [6.5, 517.0], [6.6, 517.0], [6.7, 518.0], [6.8, 518.0], [6.9, 518.0], [7.0, 519.0], [7.1, 519.0], [7.2, 519.0], [7.3, 519.0], [7.4, 520.0], [7.5, 520.0], [7.6, 520.0], [7.7, 520.0], [7.8, 521.0], [7.9, 521.0], [8.0, 521.0], [8.1, 521.0], [8.2, 521.0], [8.3, 522.0], [8.4, 522.0], [8.5, 522.0], [8.6, 522.0], [8.7, 522.0], [8.8, 523.0], [8.9, 523.0], [9.0, 523.0], [9.1, 523.0], [9.2, 523.0], [9.3, 523.0], [9.4, 524.0], [9.5, 524.0], [9.6, 524.0], [9.7, 524.0], [9.8, 524.0], [9.9, 524.0], [10.0, 525.0], [10.1, 525.0], [10.2, 525.0], [10.3, 525.0], [10.4, 526.0], [10.5, 526.0], [10.6, 526.0], [10.7, 526.0], [10.8, 526.0], [10.9, 527.0], [11.0, 527.0], [11.1, 527.0], [11.2, 527.0], [11.3, 527.0], [11.4, 527.0], [11.5, 528.0], [11.6, 528.0], [11.7, 528.0], [11.8, 528.0], [11.9, 528.0], [12.0, 528.0], [12.1, 529.0], [12.2, 529.0], [12.3, 529.0], [12.4, 529.0], [12.5, 529.0], [12.6, 529.0], [12.7, 530.0], [12.8, 530.0], [12.9, 530.0], [13.0, 530.0], [13.1, 530.0], [13.2, 531.0], [13.3, 531.0], [13.4, 531.0], [13.5, 531.0], [13.6, 531.0], [13.7, 531.0], [13.8, 531.0], [13.9, 532.0], [14.0, 532.0], [14.1, 532.0], [14.2, 532.0], [14.3, 532.0], [14.4, 532.0], [14.5, 532.0], [14.6, 533.0], [14.7, 533.0], [14.8, 533.0], [14.9, 533.0], [15.0, 533.0], [15.1, 533.0], [15.2, 533.0], [15.3, 534.0], [15.4, 534.0], [15.5, 534.0], [15.6, 534.0], [15.7, 534.0], [15.8, 534.0], [15.9, 535.0], [16.0, 535.0], [16.1, 535.0], [16.2, 535.0], [16.3, 535.0], [16.4, 535.0], [16.5, 535.0], [16.6, 536.0], [16.7, 536.0], [16.8, 536.0], [16.9, 536.0], [17.0, 536.0], [17.1, 536.0], [17.2, 537.0], [17.3, 537.0], [17.4, 537.0], [17.5, 537.0], [17.6, 537.0], [17.7, 537.0], [17.8, 537.0], [17.9, 538.0], [18.0, 538.0], [18.1, 538.0], [18.2, 538.0], [18.3, 538.0], [18.4, 538.0], [18.5, 538.0], [18.6, 539.0], [18.7, 539.0], [18.8, 539.0], [18.9, 539.0], [19.0, 539.0], [19.1, 539.0], [19.2, 540.0], [19.3, 540.0], [19.4, 540.0], [19.5, 540.0], [19.6, 540.0], [19.7, 540.0], [19.8, 540.0], [19.9, 540.0], [20.0, 541.0], [20.1, 541.0], [20.2, 541.0], [20.3, 541.0], [20.4, 541.0], [20.5, 541.0], [20.6, 542.0], [20.7, 542.0], [20.8, 542.0], [20.9, 542.0], [21.0, 542.0], [21.1, 542.0], [21.2, 543.0], [21.3, 543.0], [21.4, 543.0], [21.5, 543.0], [21.6, 543.0], [21.7, 543.0], [21.8, 543.0], [21.9, 544.0], [22.0, 544.0], [22.1, 544.0], [22.2, 544.0], [22.3, 544.0], [22.4, 544.0], [22.5, 544.0], [22.6, 544.0], [22.7, 545.0], [22.8, 545.0], [22.9, 545.0], [23.0, 545.0], [23.1, 545.0], [23.2, 545.0], [23.3, 545.0], [23.4, 546.0], [23.5, 546.0], [23.6, 546.0], [23.7, 546.0], [23.8, 546.0], [23.9, 546.0], [24.0, 546.0], [24.1, 546.0], [24.2, 547.0], [24.3, 547.0], [24.4, 547.0], [24.5, 547.0], [24.6, 547.0], [24.7, 547.0], [24.8, 547.0], [24.9, 548.0], [25.0, 548.0], [25.1, 548.0], [25.2, 548.0], [25.3, 548.0], [25.4, 548.0], [25.5, 548.0], [25.6, 549.0], [25.7, 549.0], [25.8, 549.0], [25.9, 549.0], [26.0, 549.0], [26.1, 549.0], [26.2, 549.0], [26.3, 550.0], [26.4, 550.0], [26.5, 550.0], [26.6, 550.0], [26.7, 550.0], [26.8, 550.0], [26.9, 550.0], [27.0, 550.0], [27.1, 551.0], [27.2, 551.0], [27.3, 551.0], [27.4, 551.0], [27.5, 551.0], [27.6, 551.0], [27.7, 551.0], [27.8, 551.0], [27.9, 552.0], [28.0, 552.0], [28.1, 552.0], [28.2, 552.0], [28.3, 552.0], [28.4, 552.0], [28.5, 552.0], [28.6, 552.0], [28.7, 553.0], [28.8, 553.0], [28.9, 553.0], [29.0, 553.0], [29.1, 553.0], [29.2, 553.0], [29.3, 553.0], [29.4, 553.0], [29.5, 554.0], [29.6, 554.0], [29.7, 554.0], [29.8, 554.0], [29.9, 554.0], [30.0, 554.0], [30.1, 554.0], [30.2, 554.0], [30.3, 555.0], [30.4, 555.0], [30.5, 555.0], [30.6, 555.0], [30.7, 555.0], [30.8, 555.0], [30.9, 555.0], [31.0, 556.0], [31.1, 556.0], [31.2, 556.0], [31.3, 556.0], [31.4, 556.0], [31.5, 556.0], [31.6, 556.0], [31.7, 557.0], [31.8, 557.0], [31.9, 557.0], [32.0, 557.0], [32.1, 557.0], [32.2, 557.0], [32.3, 557.0], [32.4, 558.0], [32.5, 558.0], [32.6, 558.0], [32.7, 558.0], [32.8, 558.0], [32.9, 558.0], [33.0, 558.0], [33.1, 558.0], [33.2, 558.0], [33.3, 559.0], [33.4, 559.0], [33.5, 559.0], [33.6, 559.0], [33.7, 559.0], [33.8, 559.0], [33.9, 559.0], [34.0, 559.0], [34.1, 560.0], [34.2, 560.0], [34.3, 560.0], [34.4, 560.0], [34.5, 560.0], [34.6, 560.0], [34.7, 560.0], [34.8, 560.0], [34.9, 561.0], [35.0, 561.0], [35.1, 561.0], [35.2, 561.0], [35.3, 561.0], [35.4, 561.0], [35.5, 561.0], [35.6, 561.0], [35.7, 562.0], [35.8, 562.0], [35.9, 562.0], [36.0, 562.0], [36.1, 562.0], [36.2, 562.0], [36.3, 562.0], [36.4, 563.0], [36.5, 563.0], [36.6, 563.0], [36.7, 563.0], [36.8, 563.0], [36.9, 563.0], [37.0, 563.0], [37.1, 563.0], [37.2, 564.0], [37.3, 564.0], [37.4, 564.0], [37.5, 564.0], [37.6, 564.0], [37.7, 564.0], [37.8, 564.0], [37.9, 564.0], [38.0, 565.0], [38.1, 565.0], [38.2, 565.0], [38.3, 565.0], [38.4, 565.0], [38.5, 565.0], [38.6, 566.0], [38.7, 566.0], [38.8, 566.0], [38.9, 566.0], [39.0, 566.0], [39.1, 566.0], [39.2, 566.0], [39.3, 567.0], [39.4, 567.0], [39.5, 567.0], [39.6, 567.0], [39.7, 567.0], [39.8, 567.0], [39.9, 567.0], [40.0, 567.0], [40.1, 568.0], [40.2, 568.0], [40.3, 568.0], [40.4, 568.0], [40.5, 568.0], [40.6, 568.0], [40.7, 568.0], [40.8, 568.0], [40.9, 569.0], [41.0, 569.0], [41.1, 569.0], [41.2, 569.0], [41.3, 569.0], [41.4, 569.0], [41.5, 569.0], [41.6, 569.0], [41.7, 570.0], [41.8, 570.0], [41.9, 570.0], [42.0, 570.0], [42.1, 570.0], [42.2, 570.0], [42.3, 570.0], [42.4, 571.0], [42.5, 571.0], [42.6, 571.0], [42.7, 571.0], [42.8, 571.0], [42.9, 571.0], [43.0, 571.0], [43.1, 571.0], [43.2, 572.0], [43.3, 572.0], [43.4, 572.0], [43.5, 572.0], [43.6, 572.0], [43.7, 572.0], [43.8, 573.0], [43.9, 573.0], [44.0, 573.0], [44.1, 573.0], [44.2, 573.0], [44.3, 573.0], [44.4, 573.0], [44.5, 573.0], [44.6, 574.0], [44.7, 574.0], [44.8, 574.0], [44.9, 574.0], [45.0, 574.0], [45.1, 574.0], [45.2, 574.0], [45.3, 574.0], [45.4, 575.0], [45.5, 575.0], [45.6, 575.0], [45.7, 575.0], [45.8, 575.0], [45.9, 575.0], [46.0, 575.0], [46.1, 576.0], [46.2, 576.0], [46.3, 576.0], [46.4, 576.0], [46.5, 576.0], [46.6, 576.0], [46.7, 576.0], [46.8, 576.0], [46.9, 577.0], [47.0, 577.0], [47.1, 577.0], [47.2, 577.0], [47.3, 577.0], [47.4, 577.0], [47.5, 578.0], [47.6, 578.0], [47.7, 578.0], [47.8, 578.0], [47.9, 578.0], [48.0, 578.0], [48.1, 578.0], [48.2, 578.0], [48.3, 578.0], [48.4, 579.0], [48.5, 579.0], [48.6, 579.0], [48.7, 579.0], [48.8, 579.0], [48.9, 579.0], [49.0, 579.0], [49.1, 579.0], [49.2, 579.0], [49.3, 580.0], [49.4, 580.0], [49.5, 580.0], [49.6, 580.0], [49.7, 580.0], [49.8, 580.0], [49.9, 580.0], [50.0, 580.0], [50.1, 581.0], [50.2, 581.0], [50.3, 581.0], [50.4, 581.0], [50.5, 581.0], [50.6, 581.0], [50.7, 581.0], [50.8, 582.0], [50.9, 582.0], [51.0, 582.0], [51.1, 582.0], [51.2, 582.0], [51.3, 582.0], [51.4, 582.0], [51.5, 583.0], [51.6, 583.0], [51.7, 583.0], [51.8, 583.0], [51.9, 583.0], [52.0, 583.0], [52.1, 583.0], [52.2, 583.0], [52.3, 584.0], [52.4, 584.0], [52.5, 584.0], [52.6, 584.0], [52.7, 584.0], [52.8, 584.0], [52.9, 584.0], [53.0, 584.0], [53.1, 585.0], [53.2, 585.0], [53.3, 585.0], [53.4, 585.0], [53.5, 585.0], [53.6, 585.0], [53.7, 586.0], [53.8, 586.0], [53.9, 586.0], [54.0, 586.0], [54.1, 586.0], [54.2, 586.0], [54.3, 586.0], [54.4, 587.0], [54.5, 587.0], [54.6, 587.0], [54.7, 587.0], [54.8, 587.0], [54.9, 587.0], [55.0, 587.0], [55.1, 588.0], [55.2, 588.0], [55.3, 588.0], [55.4, 588.0], [55.5, 588.0], [55.6, 588.0], [55.7, 588.0], [55.8, 589.0], [55.9, 589.0], [56.0, 589.0], [56.1, 589.0], [56.2, 589.0], [56.3, 589.0], [56.4, 589.0], [56.5, 590.0], [56.6, 590.0], [56.7, 590.0], [56.8, 590.0], [56.9, 590.0], [57.0, 590.0], [57.1, 591.0], [57.2, 591.0], [57.3, 591.0], [57.4, 591.0], [57.5, 591.0], [57.6, 591.0], [57.7, 591.0], [57.8, 592.0], [57.9, 592.0], [58.0, 592.0], [58.1, 592.0], [58.2, 592.0], [58.3, 592.0], [58.4, 592.0], [58.5, 593.0], [58.6, 593.0], [58.7, 593.0], [58.8, 593.0], [58.9, 593.0], [59.0, 593.0], [59.1, 594.0], [59.2, 594.0], [59.3, 594.0], [59.4, 594.0], [59.5, 594.0], [59.6, 594.0], [59.7, 594.0], [59.8, 594.0], [59.9, 595.0], [60.0, 595.0], [60.1, 595.0], [60.2, 595.0], [60.3, 595.0], [60.4, 595.0], [60.5, 596.0], [60.6, 596.0], [60.7, 596.0], [60.8, 596.0], [60.9, 596.0], [61.0, 597.0], [61.1, 597.0], [61.2, 597.0], [61.3, 597.0], [61.4, 597.0], [61.5, 597.0], [61.6, 597.0], [61.7, 598.0], [61.8, 598.0], [61.9, 598.0], [62.0, 598.0], [62.1, 598.0], [62.2, 599.0], [62.3, 599.0], [62.4, 599.0], [62.5, 599.0], [62.6, 599.0], [62.7, 599.0], [62.8, 600.0], [62.9, 600.0], [63.0, 600.0], [63.1, 600.0], [63.2, 600.0], [63.3, 600.0], [63.4, 600.0], [63.5, 601.0], [63.6, 601.0], [63.7, 601.0], [63.8, 601.0], [63.9, 601.0], [64.0, 602.0], [64.1, 602.0], [64.2, 602.0], [64.3, 602.0], [64.4, 602.0], [64.5, 602.0], [64.6, 602.0], [64.7, 603.0], [64.8, 603.0], [64.9, 603.0], [65.0, 603.0], [65.1, 603.0], [65.2, 604.0], [65.3, 604.0], [65.4, 604.0], [65.5, 604.0], [65.6, 604.0], [65.7, 604.0], [65.8, 605.0], [65.9, 605.0], [66.0, 605.0], [66.1, 605.0], [66.2, 605.0], [66.3, 606.0], [66.4, 606.0], [66.5, 606.0], [66.6, 606.0], [66.7, 606.0], [66.8, 607.0], [66.9, 607.0], [67.0, 607.0], [67.1, 607.0], [67.2, 607.0], [67.3, 608.0], [67.4, 608.0], [67.5, 608.0], [67.6, 608.0], [67.7, 609.0], [67.8, 609.0], [67.9, 609.0], [68.0, 609.0], [68.1, 609.0], [68.2, 610.0], [68.3, 610.0], [68.4, 610.0], [68.5, 610.0], [68.6, 610.0], [68.7, 610.0], [68.8, 611.0], [68.9, 611.0], [69.0, 611.0], [69.1, 611.0], [69.2, 611.0], [69.3, 612.0], [69.4, 612.0], [69.5, 612.0], [69.6, 612.0], [69.7, 613.0], [69.8, 613.0], [69.9, 613.0], [70.0, 613.0], [70.1, 614.0], [70.2, 614.0], [70.3, 614.0], [70.4, 614.0], [70.5, 615.0], [70.6, 615.0], [70.7, 615.0], [70.8, 615.0], [70.9, 615.0], [71.0, 616.0], [71.1, 616.0], [71.2, 616.0], [71.3, 617.0], [71.4, 617.0], [71.5, 617.0], [71.6, 617.0], [71.7, 618.0], [71.8, 618.0], [71.9, 618.0], [72.0, 619.0], [72.1, 619.0], [72.2, 619.0], [72.3, 620.0], [72.4, 620.0], [72.5, 620.0], [72.6, 620.0], [72.7, 621.0], [72.8, 621.0], [72.9, 621.0], [73.0, 622.0], [73.1, 622.0], [73.2, 622.0], [73.3, 622.0], [73.4, 623.0], [73.5, 623.0], [73.6, 623.0], [73.7, 624.0], [73.8, 624.0], [73.9, 624.0], [74.0, 625.0], [74.1, 625.0], [74.2, 625.0], [74.3, 626.0], [74.4, 626.0], [74.5, 626.0], [74.6, 626.0], [74.7, 627.0], [74.8, 627.0], [74.9, 627.0], [75.0, 627.0], [75.1, 628.0], [75.2, 628.0], [75.3, 628.0], [75.4, 629.0], [75.5, 629.0], [75.6, 629.0], [75.7, 630.0], [75.8, 630.0], [75.9, 630.0], [76.0, 631.0], [76.1, 631.0], [76.2, 632.0], [76.3, 632.0], [76.4, 632.0], [76.5, 633.0], [76.6, 633.0], [76.7, 633.0], [76.8, 634.0], [76.9, 634.0], [77.0, 634.0], [77.1, 635.0], [77.2, 635.0], [77.3, 635.0], [77.4, 636.0], [77.5, 636.0], [77.6, 636.0], [77.7, 637.0], [77.8, 637.0], [77.9, 638.0], [78.0, 638.0], [78.1, 638.0], [78.2, 639.0], [78.3, 639.0], [78.4, 640.0], [78.5, 640.0], [78.6, 640.0], [78.7, 641.0], [78.8, 641.0], [78.9, 642.0], [79.0, 642.0], [79.1, 643.0], [79.2, 643.0], [79.3, 644.0], [79.4, 644.0], [79.5, 645.0], [79.6, 645.0], [79.7, 646.0], [79.8, 646.0], [79.9, 647.0], [80.0, 648.0], [80.1, 648.0], [80.2, 649.0], [80.3, 649.0], [80.4, 650.0], [80.5, 650.0], [80.6, 651.0], [80.7, 651.0], [80.8, 652.0], [80.9, 652.0], [81.0, 653.0], [81.1, 653.0], [81.2, 654.0], [81.3, 654.0], [81.4, 655.0], [81.5, 656.0], [81.6, 657.0], [81.7, 657.0], [81.8, 658.0], [81.9, 659.0], [82.0, 659.0], [82.1, 660.0], [82.2, 660.0], [82.3, 661.0], [82.4, 662.0], [82.5, 663.0], [82.6, 663.0], [82.7, 664.0], [82.8, 665.0], [82.9, 666.0], [83.0, 667.0], [83.1, 667.0], [83.2, 668.0], [83.3, 670.0], [83.4, 670.0], [83.5, 671.0], [83.6, 672.0], [83.7, 673.0], [83.8, 673.0], [83.9, 674.0], [84.0, 675.0], [84.1, 676.0], [84.2, 677.0], [84.3, 678.0], [84.4, 679.0], [84.5, 680.0], [84.6, 682.0], [84.7, 683.0], [84.8, 684.0], [84.9, 685.0], [85.0, 686.0], [85.1, 688.0], [85.2, 690.0], [85.3, 691.0], [85.4, 693.0], [85.5, 694.0], [85.6, 695.0], [85.7, 697.0], [85.8, 699.0], [85.9, 700.0], [86.0, 701.0], [86.1, 703.0], [86.2, 705.0], [86.3, 706.0], [86.4, 707.0], [86.5, 709.0], [86.6, 710.0], [86.7, 711.0], [86.8, 712.0], [86.9, 713.0], [87.0, 715.0], [87.1, 716.0], [87.2, 717.0], [87.3, 719.0], [87.4, 720.0], [87.5, 722.0], [87.6, 724.0], [87.7, 726.0], [87.8, 729.0], [87.9, 731.0], [88.0, 733.0], [88.1, 736.0], [88.2, 739.0], [88.3, 741.0], [88.4, 745.0], [88.5, 747.0], [88.6, 750.0], [88.7, 752.0], [88.8, 755.0], [88.9, 758.0], [89.0, 762.0], [89.1, 767.0], [89.2, 770.0], [89.3, 774.0], [89.4, 780.0], [89.5, 787.0], [89.6, 791.0], [89.7, 796.0], [89.8, 802.0], [89.9, 810.0], [90.0, 815.0], [90.1, 824.0], [90.2, 831.0], [90.3, 841.0], [90.4, 848.0], [90.5, 856.0], [90.6, 873.0], [90.7, 891.0], [90.8, 921.0], [90.9, 935.0], [91.0, 957.0], [91.1, 977.0], [91.2, 998.0], [91.3, 1022.0], [91.4, 1051.0], [91.5, 1091.0], [91.6, 1117.0], [91.7, 1134.0], [91.8, 1151.0], [91.9, 1167.0], [92.0, 1180.0], [92.1, 1187.0], [92.2, 1193.0], [92.3, 1201.0], [92.4, 1206.0], [92.5, 1211.0], [92.6, 1218.0], [92.7, 1224.0], [92.8, 1228.0], [92.9, 1235.0], [93.0, 1239.0], [93.1, 1244.0], [93.2, 1249.0], [93.3, 1253.0], [93.4, 1257.0], [93.5, 1262.0], [93.6, 1265.0], [93.7, 1269.0], [93.8, 1274.0], [93.9, 1277.0], [94.0, 1280.0], [94.1, 1284.0], [94.2, 1287.0], [94.3, 1289.0], [94.4, 1292.0], [94.5, 1298.0], [94.6, 1301.0], [94.7, 1303.0], [94.8, 1306.0], [94.9, 1308.0], [95.0, 1312.0], [95.1, 1316.0], [95.2, 1319.0], [95.3, 1322.0], [95.4, 1324.0], [95.5, 1327.0], [95.6, 1330.0], [95.7, 1332.0], [95.8, 1336.0], [95.9, 1341.0], [96.0, 1344.0], [96.1, 1346.0], [96.2, 1350.0], [96.3, 1354.0], [96.4, 1358.0], [96.5, 1362.0], [96.6, 1365.0], [96.7, 1371.0], [96.8, 1376.0], [96.9, 1381.0], [97.0, 1384.0], [97.1, 1387.0], [97.2, 1391.0], [97.3, 1397.0], [97.4, 1401.0], [97.5, 1407.0], [97.6, 1413.0], [97.7, 1418.0], [97.8, 1424.0], [97.9, 1430.0], [98.0, 1435.0], [98.1, 1441.0], [98.2, 1449.0], [98.3, 1455.0], [98.4, 1466.0], [98.5, 1476.0], [98.6, 1486.0], [98.7, 1494.0], [98.8, 1510.0], [98.9, 1525.0], [99.0, 1538.0], [99.1, 1553.0], [99.2, 1560.0], [99.3, 1581.0], [99.4, 1589.0], [99.5, 1609.0], [99.6, 1625.0], [99.7, 1666.0], [99.8, 1709.0], [99.9, 1746.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
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
        data: {"result": {"minY": 1.0, "minX": 100.0, "maxY": 9661.0, "series": [{"data": [[2200.0, 1.0], [600.0, 3702.0], [2600.0, 1.0], [700.0, 618.0], [200.0, 22.0], [800.0, 153.0], [900.0, 78.0], [1000.0, 52.0], [1100.0, 121.0], [300.0, 31.0], [1200.0, 360.0], [1300.0, 454.0], [1400.0, 220.0], [1500.0, 112.0], [100.0, 42.0], [400.0, 287.0], [1600.0, 54.0], [1700.0, 30.0], [1800.0, 3.0], [500.0, 9661.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 2600.0, "title": "Response Time Distribution"}},
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
        data: {"result": {"minY": 201.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 15401.0, "series": [{"data": [[0.0, 400.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 15401.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [[2.0, 201.0]], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 2.0, "title": "Synthetic Response Times Distribution"}},
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
        data: {"result": {"minY": 31.275342465753404, "minX": 1.7503902E12, "maxY": 35.0, "series": [{"data": [[1.75039038E12, 35.0], [1.7503902E12, 31.275342465753404], [1.7503905E12, 34.490598290598285], [1.75039032E12, 35.0], [1.75039044E12, 35.0], [1.75039026E12, 35.0]], "isOverall": false, "label": "线程组", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7503905E12, "title": "Active Threads Over Time"}},
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
        data: {"result": {"minY": 312.8571428571429, "minX": 1.0, "maxY": 1250.0, "series": [{"data": [[2.0, 728.5], [32.0, 783.1666666666667], [33.0, 837.0], [34.0, 894.125], [35.0, 653.2401063762404], [3.0, 752.5], [4.0, 484.0], [5.0, 474.0], [6.0, 312.8571428571429], [7.0, 343.16666666666663], [8.0, 335.0], [9.0, 386.33333333333337], [10.0, 360.0], [11.0, 384.6666666666667], [12.0, 410.0], [13.0, 414.5714285714286], [14.0, 508.83333333333337], [15.0, 520.6666666666666], [16.0, 522.3333333333334], [1.0, 1250.0], [17.0, 543.0], [18.0, 529.7142857142858], [19.0, 536.0], [20.0, 552.5], [21.0, 518.0], [22.0, 588.0], [23.0, 553.375], [24.0, 572.875], [25.0, 596.2857142857143], [26.0, 650.0], [27.0, 597.8888888888889], [28.0, 634.0], [29.0, 684.3333333333333], [30.0, 707.0], [31.0, 737.8333333333333]], "isOverall": false, "label": "HTTP请求配置", "isController": false}, {"data": [[34.79283839520049, 651.974565679287]], "isOverall": false, "label": "HTTP请求配置-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 35.0, "title": "Time VS Threads"}},
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
        data : {"result": {"minY": 2998.4333333333334, "minX": 1.7503902E12, "maxY": 43011.583333333336, "series": [{"data": [[1.75039038E12, 41275.0], [1.7503902E12, 8577.5], [1.7503905E12, 13747.5], [1.75039032E12, 41061.566666666666], [1.75039044E12, 40285.583333333336], [1.75039026E12, 43011.583333333336]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.75039038E12, 14433.9], [1.7503902E12, 2998.4333333333334], [1.7503905E12, 4806.0], [1.75039032E12, 14360.133333333333], [1.75039044E12, 14088.966666666667], [1.75039026E12, 15041.966666666667]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7503905E12, "title": "Bytes Throughput Over Time"}},
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
        data: {"result": {"minY": 573.8577280174768, "minX": 1.7503902E12, "maxY": 1368.6683760683743, "series": [{"data": [[1.75039038E12, 597.773192942517], [1.7503902E12, 610.4575342465749], [1.7503905E12, 1368.6683760683743], [1.75039032E12, 600.4144736842105], [1.75039044E12, 607.822157434404], [1.75039026E12, 573.8577280174768]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7503905E12, "title": "Response Time Over Time"}},
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
        data: {"result": {"minY": 573.6193336974334, "minX": 1.7503902E12, "maxY": 1368.1239316239332, "series": [{"data": [[1.75039038E12, 597.5071143995457], [1.7503902E12, 610.2575342465752], [1.7503905E12, 1368.1239316239332], [1.75039032E12, 600.1510297482829], [1.75039044E12, 607.5717201166161], [1.75039026E12, 573.6193336974334]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7503905E12, "title": "Latencies Over Time"}},
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
        data: {"result": {"minY": 0.011098463289698346, "minX": 1.7503902E12, "maxY": 0.08493150684931505, "series": [{"data": [[1.75039038E12, 0.011098463289698346], [1.7503902E12, 0.08493150684931505], [1.7503905E12, 0.04017094017094018], [1.75039032E12, 0.01229977116704806], [1.75039044E12, 0.012244897959183666], [1.75039026E12, 0.012834516657564179]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7503905E12, "title": "Connect Time Over Time"}},
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
        data: {"result": {"minY": 121.0, "minX": 1.7503902E12, "maxY": 2648.0, "series": [{"data": [[1.75039038E12, 1358.0], [1.7503902E12, 1055.0], [1.7503905E12, 2648.0], [1.75039032E12, 1070.0], [1.75039044E12, 2223.0], [1.75039026E12, 1027.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.75039038E12, 698.0], [1.7503902E12, 768.9], [1.7503905E12, 1572.0], [1.75039032E12, 657.0], [1.75039044E12, 679.9000000000001], [1.75039026E12, 635.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.75039038E12, 947.8499999999999], [1.7503902E12, 887.0699999999998], [1.7503905E12, 1757.58], [1.75039032E12, 886.2999999999975], [1.75039044E12, 1346.38], [1.75039026E12, 732.0]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.75039038E12, 759.0], [1.7503902E12, 826.0], [1.7503905E12, 1638.9], [1.75039032E12, 692.1499999999996], [1.75039044E12, 1206.0], [1.75039026E12, 657.0]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.75039038E12, 157.0], [1.7503902E12, 121.0], [1.7503905E12, 370.0], [1.75039032E12, 147.0], [1.75039044E12, 159.0], [1.75039026E12, 145.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.75039038E12, 574.0], [1.7503902E12, 641.0], [1.7503905E12, 1347.0], [1.75039032E12, 589.0], [1.75039044E12, 553.0], [1.75039026E12, 574.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7503905E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
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
    data: {"result": {"minY": 172.0, "minX": 3.0, "maxY": 1595.5, "series": [{"data": [[34.0, 944.0], [37.0, 466.5], [38.0, 344.0], [41.0, 705.5], [40.0, 563.0], [42.0, 782.0], [44.0, 783.0], [47.0, 708.0], [3.0, 172.0], [48.0, 836.5], [49.0, 617.0], [50.0, 687.0], [51.0, 577.5], [53.0, 666.0], [52.0, 647.0], [54.0, 610.0], [55.0, 609.0], [56.0, 623.0], [57.0, 603.0], [59.0, 590.0], [58.0, 595.0], [61.0, 584.0], [60.0, 582.0], [63.0, 551.0], [62.0, 573.0], [67.0, 530.0], [66.0, 535.0], [65.0, 536.0], [64.0, 546.0], [69.0, 516.0], [68.0, 518.0], [18.0, 1591.5], [19.0, 1322.0], [20.0, 1595.5], [21.0, 1416.0], [22.0, 1439.0], [24.0, 1482.0], [25.0, 1347.0], [26.0, 1338.5], [27.0, 1364.0], [28.0, 1302.0], [29.0, 1262.0], [30.0, 1181.5], [31.0, 1187.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 69.0, "title": "Response Time Vs Request"}},
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
    data: {"result": {"minY": 171.0, "minX": 3.0, "maxY": 1595.0, "series": [{"data": [[34.0, 943.5], [37.0, 466.0], [38.0, 343.5], [41.0, 705.5], [40.0, 563.0], [42.0, 780.5], [44.0, 782.5], [47.0, 708.0], [3.0, 171.0], [48.0, 836.5], [49.0, 616.0], [50.0, 685.5], [51.0, 577.5], [53.0, 666.0], [52.0, 647.0], [54.0, 609.5], [55.0, 609.0], [56.0, 623.0], [57.0, 603.0], [59.0, 590.0], [58.0, 595.0], [61.0, 584.0], [60.0, 582.0], [63.0, 551.0], [62.0, 573.0], [67.0, 530.0], [66.0, 535.0], [65.0, 536.0], [64.0, 546.0], [69.0, 516.0], [68.0, 517.5], [18.0, 1591.5], [19.0, 1322.0], [20.0, 1595.0], [21.0, 1415.5], [22.0, 1438.0], [24.0, 1481.0], [25.0, 1347.0], [26.0, 1338.0], [27.0, 1364.0], [28.0, 1302.0], [29.0, 1261.0], [30.0, 1181.5], [31.0, 1186.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 69.0, "title": "Latencies Vs Request"}},
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
        data: {"result": {"minY": 12.75, "minX": 1.7503902E12, "maxY": 61.03333333333333, "series": [{"data": [[1.75039038E12, 58.56666666666667], [1.7503902E12, 12.75], [1.7503905E12, 18.916666666666668], [1.75039032E12, 58.266666666666666], [1.75039044E12, 57.166666666666664], [1.75039026E12, 61.03333333333333]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7503905E12, "title": "Hits Per Second"}},
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
        data: {"result": {"minY": 12.166666666666666, "minX": 1.7503902E12, "maxY": 61.03333333333333, "series": [{"data": [[1.75039038E12, 58.56666666666667], [1.7503902E12, 12.166666666666666], [1.7503905E12, 19.5], [1.75039032E12, 58.266666666666666], [1.75039044E12, 57.166666666666664], [1.75039026E12, 61.03333333333333]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7503905E12, "title": "Codes Per Second"}},
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
        data: {"result": {"minY": 12.166666666666666, "minX": 1.7503902E12, "maxY": 61.03333333333333, "series": [{"data": [[1.75039038E12, 58.56666666666667], [1.7503902E12, 12.166666666666666], [1.7503905E12, 19.5], [1.75039032E12, 58.266666666666666], [1.75039044E12, 57.166666666666664], [1.75039026E12, 61.03333333333333]], "isOverall": false, "label": "HTTP请求配置-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7503905E12, "title": "Transactions Per Second"}},
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
        data: {"result": {"minY": 12.166666666666666, "minX": 1.7503902E12, "maxY": 61.03333333333333, "series": [{"data": [[1.75039038E12, 58.56666666666667], [1.7503902E12, 12.166666666666666], [1.7503905E12, 19.5], [1.75039032E12, 58.266666666666666], [1.75039044E12, 57.166666666666664], [1.75039026E12, 61.03333333333333]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7503905E12, "title": "Total Transactions Per Second"}},
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

