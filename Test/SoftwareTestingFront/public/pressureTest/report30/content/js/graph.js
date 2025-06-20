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
        data: {"result": {"minY": 110.0, "minX": 0.0, "maxY": 1672.0, "series": [{"data": [[0.0, 110.0], [0.1, 134.0], [0.2, 174.0], [0.3, 198.0], [0.4, 260.0], [0.5, 343.0], [0.6, 382.0], [0.7, 386.0], [0.8, 388.0], [0.9, 390.0], [1.0, 392.0], [1.1, 393.0], [1.2, 394.0], [1.3, 395.0], [1.4, 396.0], [1.5, 397.0], [1.6, 398.0], [1.7, 399.0], [1.8, 400.0], [1.9, 400.0], [2.0, 401.0], [2.1, 402.0], [2.2, 402.0], [2.3, 403.0], [2.4, 404.0], [2.5, 404.0], [2.6, 405.0], [2.7, 405.0], [2.8, 406.0], [2.9, 406.0], [3.0, 407.0], [3.1, 407.0], [3.2, 408.0], [3.3, 408.0], [3.4, 409.0], [3.5, 409.0], [3.6, 410.0], [3.7, 410.0], [3.8, 411.0], [3.9, 411.0], [4.0, 411.0], [4.1, 412.0], [4.2, 412.0], [4.3, 413.0], [4.4, 413.0], [4.5, 414.0], [4.6, 414.0], [4.7, 414.0], [4.8, 415.0], [4.9, 415.0], [5.0, 415.0], [5.1, 416.0], [5.2, 416.0], [5.3, 416.0], [5.4, 416.0], [5.5, 417.0], [5.6, 417.0], [5.7, 417.0], [5.8, 418.0], [5.9, 418.0], [6.0, 418.0], [6.1, 419.0], [6.2, 419.0], [6.3, 419.0], [6.4, 420.0], [6.5, 420.0], [6.6, 420.0], [6.7, 421.0], [6.8, 421.0], [6.9, 421.0], [7.0, 421.0], [7.1, 422.0], [7.2, 422.0], [7.3, 422.0], [7.4, 423.0], [7.5, 423.0], [7.6, 423.0], [7.7, 423.0], [7.8, 424.0], [7.9, 424.0], [8.0, 424.0], [8.1, 424.0], [8.2, 425.0], [8.3, 425.0], [8.4, 425.0], [8.5, 425.0], [8.6, 426.0], [8.7, 426.0], [8.8, 426.0], [8.9, 427.0], [9.0, 427.0], [9.1, 427.0], [9.2, 428.0], [9.3, 428.0], [9.4, 428.0], [9.5, 428.0], [9.6, 429.0], [9.7, 429.0], [9.8, 429.0], [9.9, 429.0], [10.0, 429.0], [10.1, 430.0], [10.2, 430.0], [10.3, 430.0], [10.4, 430.0], [10.5, 431.0], [10.6, 431.0], [10.7, 431.0], [10.8, 431.0], [10.9, 431.0], [11.0, 431.0], [11.1, 432.0], [11.2, 432.0], [11.3, 432.0], [11.4, 432.0], [11.5, 432.0], [11.6, 433.0], [11.7, 433.0], [11.8, 433.0], [11.9, 433.0], [12.0, 433.0], [12.1, 434.0], [12.2, 434.0], [12.3, 434.0], [12.4, 434.0], [12.5, 434.0], [12.6, 434.0], [12.7, 434.0], [12.8, 435.0], [12.9, 435.0], [13.0, 435.0], [13.1, 435.0], [13.2, 435.0], [13.3, 436.0], [13.4, 436.0], [13.5, 436.0], [13.6, 436.0], [13.7, 436.0], [13.8, 436.0], [13.9, 437.0], [14.0, 437.0], [14.1, 437.0], [14.2, 437.0], [14.3, 437.0], [14.4, 437.0], [14.5, 438.0], [14.6, 438.0], [14.7, 438.0], [14.8, 438.0], [14.9, 438.0], [15.0, 438.0], [15.1, 438.0], [15.2, 439.0], [15.3, 439.0], [15.4, 439.0], [15.5, 439.0], [15.6, 439.0], [15.7, 439.0], [15.8, 440.0], [15.9, 440.0], [16.0, 440.0], [16.1, 440.0], [16.2, 440.0], [16.3, 440.0], [16.4, 441.0], [16.5, 441.0], [16.6, 441.0], [16.7, 441.0], [16.8, 441.0], [16.9, 441.0], [17.0, 441.0], [17.1, 441.0], [17.2, 442.0], [17.3, 442.0], [17.4, 442.0], [17.5, 442.0], [17.6, 442.0], [17.7, 442.0], [17.8, 442.0], [17.9, 442.0], [18.0, 443.0], [18.1, 443.0], [18.2, 443.0], [18.3, 443.0], [18.4, 443.0], [18.5, 443.0], [18.6, 443.0], [18.7, 444.0], [18.8, 444.0], [18.9, 444.0], [19.0, 444.0], [19.1, 444.0], [19.2, 444.0], [19.3, 444.0], [19.4, 444.0], [19.5, 444.0], [19.6, 445.0], [19.7, 445.0], [19.8, 445.0], [19.9, 445.0], [20.0, 445.0], [20.1, 445.0], [20.2, 445.0], [20.3, 445.0], [20.4, 445.0], [20.5, 446.0], [20.6, 446.0], [20.7, 446.0], [20.8, 446.0], [20.9, 446.0], [21.0, 446.0], [21.1, 446.0], [21.2, 446.0], [21.3, 446.0], [21.4, 447.0], [21.5, 447.0], [21.6, 447.0], [21.7, 447.0], [21.8, 447.0], [21.9, 447.0], [22.0, 447.0], [22.1, 447.0], [22.2, 447.0], [22.3, 447.0], [22.4, 448.0], [22.5, 448.0], [22.6, 448.0], [22.7, 448.0], [22.8, 448.0], [22.9, 448.0], [23.0, 448.0], [23.1, 448.0], [23.2, 449.0], [23.3, 449.0], [23.4, 449.0], [23.5, 449.0], [23.6, 449.0], [23.7, 449.0], [23.8, 449.0], [23.9, 449.0], [24.0, 449.0], [24.1, 449.0], [24.2, 450.0], [24.3, 450.0], [24.4, 450.0], [24.5, 450.0], [24.6, 450.0], [24.7, 450.0], [24.8, 450.0], [24.9, 450.0], [25.0, 450.0], [25.1, 450.0], [25.2, 451.0], [25.3, 451.0], [25.4, 451.0], [25.5, 451.0], [25.6, 451.0], [25.7, 451.0], [25.8, 451.0], [25.9, 451.0], [26.0, 451.0], [26.1, 451.0], [26.2, 451.0], [26.3, 452.0], [26.4, 452.0], [26.5, 452.0], [26.6, 452.0], [26.7, 452.0], [26.8, 452.0], [26.9, 452.0], [27.0, 452.0], [27.1, 452.0], [27.2, 453.0], [27.3, 453.0], [27.4, 453.0], [27.5, 453.0], [27.6, 453.0], [27.7, 453.0], [27.8, 453.0], [27.9, 453.0], [28.0, 453.0], [28.1, 453.0], [28.2, 453.0], [28.3, 454.0], [28.4, 454.0], [28.5, 454.0], [28.6, 454.0], [28.7, 454.0], [28.8, 454.0], [28.9, 454.0], [29.0, 454.0], [29.1, 454.0], [29.2, 455.0], [29.3, 455.0], [29.4, 455.0], [29.5, 455.0], [29.6, 455.0], [29.7, 455.0], [29.8, 455.0], [29.9, 455.0], [30.0, 455.0], [30.1, 455.0], [30.2, 456.0], [30.3, 456.0], [30.4, 456.0], [30.5, 456.0], [30.6, 456.0], [30.7, 456.0], [30.8, 456.0], [30.9, 456.0], [31.0, 456.0], [31.1, 456.0], [31.2, 456.0], [31.3, 457.0], [31.4, 457.0], [31.5, 457.0], [31.6, 457.0], [31.7, 457.0], [31.8, 457.0], [31.9, 457.0], [32.0, 457.0], [32.1, 457.0], [32.2, 457.0], [32.3, 457.0], [32.4, 458.0], [32.5, 458.0], [32.6, 458.0], [32.7, 458.0], [32.8, 458.0], [32.9, 458.0], [33.0, 458.0], [33.1, 458.0], [33.2, 458.0], [33.3, 459.0], [33.4, 459.0], [33.5, 459.0], [33.6, 459.0], [33.7, 459.0], [33.8, 459.0], [33.9, 459.0], [34.0, 459.0], [34.1, 459.0], [34.2, 460.0], [34.3, 460.0], [34.4, 460.0], [34.5, 460.0], [34.6, 460.0], [34.7, 460.0], [34.8, 460.0], [34.9, 460.0], [35.0, 460.0], [35.1, 460.0], [35.2, 461.0], [35.3, 461.0], [35.4, 461.0], [35.5, 461.0], [35.6, 461.0], [35.7, 461.0], [35.8, 461.0], [35.9, 461.0], [36.0, 461.0], [36.1, 461.0], [36.2, 462.0], [36.3, 462.0], [36.4, 462.0], [36.5, 462.0], [36.6, 462.0], [36.7, 462.0], [36.8, 462.0], [36.9, 462.0], [37.0, 462.0], [37.1, 462.0], [37.2, 462.0], [37.3, 463.0], [37.4, 463.0], [37.5, 463.0], [37.6, 463.0], [37.7, 463.0], [37.8, 463.0], [37.9, 463.0], [38.0, 463.0], [38.1, 463.0], [38.2, 464.0], [38.3, 464.0], [38.4, 464.0], [38.5, 464.0], [38.6, 464.0], [38.7, 464.0], [38.8, 464.0], [38.9, 464.0], [39.0, 464.0], [39.1, 464.0], [39.2, 465.0], [39.3, 465.0], [39.4, 465.0], [39.5, 465.0], [39.6, 465.0], [39.7, 465.0], [39.8, 465.0], [39.9, 465.0], [40.0, 465.0], [40.1, 465.0], [40.2, 466.0], [40.3, 466.0], [40.4, 466.0], [40.5, 466.0], [40.6, 466.0], [40.7, 466.0], [40.8, 466.0], [40.9, 466.0], [41.0, 466.0], [41.1, 467.0], [41.2, 467.0], [41.3, 467.0], [41.4, 467.0], [41.5, 467.0], [41.6, 467.0], [41.7, 467.0], [41.8, 467.0], [41.9, 468.0], [42.0, 468.0], [42.1, 468.0], [42.2, 468.0], [42.3, 468.0], [42.4, 468.0], [42.5, 468.0], [42.6, 468.0], [42.7, 468.0], [42.8, 469.0], [42.9, 469.0], [43.0, 469.0], [43.1, 469.0], [43.2, 469.0], [43.3, 469.0], [43.4, 469.0], [43.5, 469.0], [43.6, 469.0], [43.7, 469.0], [43.8, 469.0], [43.9, 470.0], [44.0, 470.0], [44.1, 470.0], [44.2, 470.0], [44.3, 470.0], [44.4, 470.0], [44.5, 470.0], [44.6, 470.0], [44.7, 470.0], [44.8, 471.0], [44.9, 471.0], [45.0, 471.0], [45.1, 471.0], [45.2, 471.0], [45.3, 471.0], [45.4, 471.0], [45.5, 472.0], [45.6, 472.0], [45.7, 472.0], [45.8, 472.0], [45.9, 472.0], [46.0, 472.0], [46.1, 472.0], [46.2, 472.0], [46.3, 473.0], [46.4, 473.0], [46.5, 473.0], [46.6, 473.0], [46.7, 473.0], [46.8, 473.0], [46.9, 473.0], [47.0, 473.0], [47.1, 473.0], [47.2, 474.0], [47.3, 474.0], [47.4, 474.0], [47.5, 474.0], [47.6, 474.0], [47.7, 474.0], [47.8, 474.0], [47.9, 474.0], [48.0, 475.0], [48.1, 475.0], [48.2, 475.0], [48.3, 475.0], [48.4, 475.0], [48.5, 475.0], [48.6, 475.0], [48.7, 475.0], [48.8, 476.0], [48.9, 476.0], [49.0, 476.0], [49.1, 476.0], [49.2, 476.0], [49.3, 476.0], [49.4, 477.0], [49.5, 477.0], [49.6, 477.0], [49.7, 477.0], [49.8, 477.0], [49.9, 477.0], [50.0, 477.0], [50.1, 478.0], [50.2, 478.0], [50.3, 478.0], [50.4, 478.0], [50.5, 478.0], [50.6, 478.0], [50.7, 478.0], [50.8, 479.0], [50.9, 479.0], [51.0, 479.0], [51.1, 479.0], [51.2, 479.0], [51.3, 479.0], [51.4, 479.0], [51.5, 480.0], [51.6, 480.0], [51.7, 480.0], [51.8, 480.0], [51.9, 480.0], [52.0, 480.0], [52.1, 480.0], [52.2, 481.0], [52.3, 481.0], [52.4, 481.0], [52.5, 481.0], [52.6, 481.0], [52.7, 481.0], [52.8, 481.0], [52.9, 482.0], [53.0, 482.0], [53.1, 482.0], [53.2, 482.0], [53.3, 482.0], [53.4, 482.0], [53.5, 483.0], [53.6, 483.0], [53.7, 483.0], [53.8, 483.0], [53.9, 483.0], [54.0, 483.0], [54.1, 483.0], [54.2, 484.0], [54.3, 484.0], [54.4, 484.0], [54.5, 484.0], [54.6, 484.0], [54.7, 484.0], [54.8, 484.0], [54.9, 485.0], [55.0, 485.0], [55.1, 485.0], [55.2, 485.0], [55.3, 485.0], [55.4, 486.0], [55.5, 486.0], [55.6, 486.0], [55.7, 486.0], [55.8, 486.0], [55.9, 486.0], [56.0, 486.0], [56.1, 487.0], [56.2, 487.0], [56.3, 487.0], [56.4, 487.0], [56.5, 487.0], [56.6, 487.0], [56.7, 488.0], [56.8, 488.0], [56.9, 488.0], [57.0, 488.0], [57.1, 488.0], [57.2, 488.0], [57.3, 489.0], [57.4, 489.0], [57.5, 489.0], [57.6, 489.0], [57.7, 489.0], [57.8, 489.0], [57.9, 490.0], [58.0, 490.0], [58.1, 490.0], [58.2, 490.0], [58.3, 490.0], [58.4, 491.0], [58.5, 491.0], [58.6, 491.0], [58.7, 491.0], [58.8, 491.0], [58.9, 492.0], [59.0, 492.0], [59.1, 492.0], [59.2, 492.0], [59.3, 492.0], [59.4, 492.0], [59.5, 493.0], [59.6, 493.0], [59.7, 493.0], [59.8, 493.0], [59.9, 493.0], [60.0, 493.0], [60.1, 494.0], [60.2, 494.0], [60.3, 494.0], [60.4, 494.0], [60.5, 494.0], [60.6, 495.0], [60.7, 495.0], [60.8, 495.0], [60.9, 495.0], [61.0, 495.0], [61.1, 496.0], [61.2, 496.0], [61.3, 496.0], [61.4, 496.0], [61.5, 497.0], [61.6, 497.0], [61.7, 497.0], [61.8, 497.0], [61.9, 498.0], [62.0, 498.0], [62.1, 498.0], [62.2, 498.0], [62.3, 498.0], [62.4, 499.0], [62.5, 499.0], [62.6, 499.0], [62.7, 499.0], [62.8, 500.0], [62.9, 500.0], [63.0, 500.0], [63.1, 500.0], [63.2, 500.0], [63.3, 501.0], [63.4, 501.0], [63.5, 501.0], [63.6, 501.0], [63.7, 502.0], [63.8, 502.0], [63.9, 502.0], [64.0, 502.0], [64.1, 502.0], [64.2, 503.0], [64.3, 503.0], [64.4, 503.0], [64.5, 503.0], [64.6, 503.0], [64.7, 504.0], [64.8, 504.0], [64.9, 504.0], [65.0, 504.0], [65.1, 504.0], [65.2, 505.0], [65.3, 505.0], [65.4, 505.0], [65.5, 505.0], [65.6, 505.0], [65.7, 506.0], [65.8, 506.0], [65.9, 506.0], [66.0, 507.0], [66.1, 507.0], [66.2, 507.0], [66.3, 507.0], [66.4, 508.0], [66.5, 508.0], [66.6, 508.0], [66.7, 508.0], [66.8, 509.0], [66.9, 509.0], [67.0, 509.0], [67.1, 509.0], [67.2, 510.0], [67.3, 510.0], [67.4, 510.0], [67.5, 510.0], [67.6, 511.0], [67.7, 511.0], [67.8, 511.0], [67.9, 511.0], [68.0, 512.0], [68.1, 512.0], [68.2, 512.0], [68.3, 512.0], [68.4, 513.0], [68.5, 513.0], [68.6, 513.0], [68.7, 513.0], [68.8, 514.0], [68.9, 514.0], [69.0, 514.0], [69.1, 515.0], [69.2, 515.0], [69.3, 515.0], [69.4, 515.0], [69.5, 516.0], [69.6, 516.0], [69.7, 516.0], [69.8, 516.0], [69.9, 517.0], [70.0, 517.0], [70.1, 517.0], [70.2, 517.0], [70.3, 517.0], [70.4, 518.0], [70.5, 518.0], [70.6, 518.0], [70.7, 518.0], [70.8, 519.0], [70.9, 519.0], [71.0, 519.0], [71.1, 520.0], [71.2, 520.0], [71.3, 520.0], [71.4, 520.0], [71.5, 521.0], [71.6, 521.0], [71.7, 521.0], [71.8, 521.0], [71.9, 522.0], [72.0, 522.0], [72.1, 522.0], [72.2, 523.0], [72.3, 523.0], [72.4, 523.0], [72.5, 524.0], [72.6, 524.0], [72.7, 524.0], [72.8, 525.0], [72.9, 525.0], [73.0, 525.0], [73.1, 526.0], [73.2, 526.0], [73.3, 526.0], [73.4, 527.0], [73.5, 527.0], [73.6, 527.0], [73.7, 527.0], [73.8, 528.0], [73.9, 528.0], [74.0, 528.0], [74.1, 529.0], [74.2, 529.0], [74.3, 529.0], [74.4, 530.0], [74.5, 530.0], [74.6, 531.0], [74.7, 531.0], [74.8, 531.0], [74.9, 531.0], [75.0, 532.0], [75.1, 532.0], [75.2, 532.0], [75.3, 533.0], [75.4, 533.0], [75.5, 533.0], [75.6, 534.0], [75.7, 534.0], [75.8, 535.0], [75.9, 535.0], [76.0, 535.0], [76.1, 536.0], [76.2, 536.0], [76.3, 536.0], [76.4, 536.0], [76.5, 537.0], [76.6, 537.0], [76.7, 538.0], [76.8, 538.0], [76.9, 539.0], [77.0, 539.0], [77.1, 539.0], [77.2, 540.0], [77.3, 540.0], [77.4, 541.0], [77.5, 541.0], [77.6, 541.0], [77.7, 542.0], [77.8, 542.0], [77.9, 543.0], [78.0, 543.0], [78.1, 544.0], [78.2, 544.0], [78.3, 545.0], [78.4, 545.0], [78.5, 546.0], [78.6, 546.0], [78.7, 547.0], [78.8, 547.0], [78.9, 547.0], [79.0, 548.0], [79.1, 548.0], [79.2, 549.0], [79.3, 549.0], [79.4, 550.0], [79.5, 550.0], [79.6, 551.0], [79.7, 551.0], [79.8, 552.0], [79.9, 552.0], [80.0, 552.0], [80.1, 553.0], [80.2, 553.0], [80.3, 554.0], [80.4, 554.0], [80.5, 555.0], [80.6, 555.0], [80.7, 556.0], [80.8, 557.0], [80.9, 557.0], [81.0, 558.0], [81.1, 558.0], [81.2, 559.0], [81.3, 559.0], [81.4, 560.0], [81.5, 561.0], [81.6, 561.0], [81.7, 562.0], [81.8, 562.0], [81.9, 563.0], [82.0, 563.0], [82.1, 564.0], [82.2, 565.0], [82.3, 565.0], [82.4, 566.0], [82.5, 567.0], [82.6, 567.0], [82.7, 568.0], [82.8, 569.0], [82.9, 569.0], [83.0, 570.0], [83.1, 571.0], [83.2, 571.0], [83.3, 572.0], [83.4, 572.0], [83.5, 573.0], [83.6, 574.0], [83.7, 574.0], [83.8, 575.0], [83.9, 575.0], [84.0, 576.0], [84.1, 577.0], [84.2, 578.0], [84.3, 578.0], [84.4, 579.0], [84.5, 580.0], [84.6, 581.0], [84.7, 581.0], [84.8, 582.0], [84.9, 583.0], [85.0, 584.0], [85.1, 585.0], [85.2, 585.0], [85.3, 586.0], [85.4, 587.0], [85.5, 588.0], [85.6, 589.0], [85.7, 590.0], [85.8, 591.0], [85.9, 592.0], [86.0, 593.0], [86.1, 594.0], [86.2, 594.0], [86.3, 595.0], [86.4, 596.0], [86.5, 597.0], [86.6, 598.0], [86.7, 599.0], [86.8, 600.0], [86.9, 601.0], [87.0, 601.0], [87.1, 603.0], [87.2, 604.0], [87.3, 605.0], [87.4, 606.0], [87.5, 607.0], [87.6, 609.0], [87.7, 609.0], [87.8, 611.0], [87.9, 612.0], [88.0, 613.0], [88.1, 614.0], [88.2, 615.0], [88.3, 617.0], [88.4, 617.0], [88.5, 619.0], [88.6, 620.0], [88.7, 621.0], [88.8, 622.0], [88.9, 623.0], [89.0, 624.0], [89.1, 625.0], [89.2, 626.0], [89.3, 627.0], [89.4, 628.0], [89.5, 629.0], [89.6, 630.0], [89.7, 631.0], [89.8, 631.0], [89.9, 632.0], [90.0, 633.0], [90.1, 634.0], [90.2, 635.0], [90.3, 636.0], [90.4, 637.0], [90.5, 637.0], [90.6, 638.0], [90.7, 640.0], [90.8, 641.0], [90.9, 641.0], [91.0, 642.0], [91.1, 644.0], [91.2, 644.0], [91.3, 645.0], [91.4, 646.0], [91.5, 648.0], [91.6, 649.0], [91.7, 650.0], [91.8, 651.0], [91.9, 652.0], [92.0, 653.0], [92.1, 654.0], [92.2, 655.0], [92.3, 657.0], [92.4, 658.0], [92.5, 660.0], [92.6, 662.0], [92.7, 664.0], [92.8, 666.0], [92.9, 667.0], [93.0, 669.0], [93.1, 671.0], [93.2, 673.0], [93.3, 675.0], [93.4, 678.0], [93.5, 680.0], [93.6, 682.0], [93.7, 685.0], [93.8, 686.0], [93.9, 688.0], [94.0, 691.0], [94.1, 693.0], [94.2, 695.0], [94.3, 697.0], [94.4, 699.0], [94.5, 702.0], [94.6, 704.0], [94.7, 707.0], [94.8, 709.0], [94.9, 711.0], [95.0, 713.0], [95.1, 716.0], [95.2, 718.0], [95.3, 720.0], [95.4, 722.0], [95.5, 724.0], [95.6, 726.0], [95.7, 729.0], [95.8, 732.0], [95.9, 736.0], [96.0, 738.0], [96.1, 741.0], [96.2, 743.0], [96.3, 746.0], [96.4, 749.0], [96.5, 752.0], [96.6, 755.0], [96.7, 759.0], [96.8, 764.0], [96.9, 768.0], [97.0, 772.0], [97.1, 775.0], [97.2, 780.0], [97.3, 786.0], [97.4, 792.0], [97.5, 796.0], [97.6, 803.0], [97.7, 809.0], [97.8, 813.0], [97.9, 819.0], [98.0, 822.0], [98.1, 826.0], [98.2, 830.0], [98.3, 838.0], [98.4, 844.0], [98.5, 851.0], [98.6, 859.0], [98.7, 869.0], [98.8, 881.0], [98.9, 894.0], [99.0, 909.0], [99.1, 921.0], [99.2, 940.0], [99.3, 956.0], [99.4, 969.0], [99.5, 988.0], [99.6, 1014.0], [99.7, 1051.0], [99.8, 1088.0], [99.9, 1182.0], [100.0, 1672.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
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
        data: {"result": {"minY": 2.0, "minX": 100.0, "maxY": 10726.0, "series": [{"data": [[600.0, 1341.0], [700.0, 552.0], [200.0, 29.0], [800.0, 242.0], [900.0, 108.0], [1000.0, 49.0], [1100.0, 18.0], [300.0, 232.0], [1200.0, 4.0], [1300.0, 3.0], [1400.0, 2.0], [1500.0, 2.0], [100.0, 53.0], [400.0, 10726.0], [1600.0, 2.0], [500.0, 4218.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 1600.0, "title": "Response Time Distribution"}},
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
        data: {"result": {"minY": 4.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 11115.0, "series": [{"data": [[0.0, 11115.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 6462.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [[2.0, 4.0]], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 2.0, "title": "Synthetic Response Times Distribution"}},
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
        data: {"result": {"minY": 26.55625990491285, "minX": 1.75038978E12, "maxY": 30.0, "series": [{"data": [[1.7503899E12, 30.0], [1.75038984E12, 30.0], [1.75039002E12, 30.0], [1.75038996E12, 30.0], [1.75038978E12, 26.55625990491285], [1.75039008E12, 29.82424242424242]], "isOverall": false, "label": "线程组", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039008E12, "title": "Active Threads Over Time"}},
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
        data: {"result": {"minY": 193.4, "minX": 1.0, "maxY": 700.0, "series": [{"data": [[2.0, 435.0], [3.0, 350.0], [4.0, 292.0], [5.0, 194.875], [6.0, 211.16666666666669], [7.0, 193.4], [8.0, 235.42857142857144], [9.0, 291.66666666666663], [10.0, 297.85714285714283], [11.0, 321.0], [12.0, 320.22222222222223], [13.0, 360.1666666666667], [14.0, 416.83333333333337], [15.0, 444.5], [16.0, 453.85714285714283], [1.0, 700.0], [17.0, 447.1666666666667], [18.0, 460.0], [19.0, 494.5714285714286], [20.0, 512.0], [21.0, 555.4285714285714], [22.0, 569.2857142857142], [23.0, 574.875], [24.0, 581.3333333333333], [25.0, 588.5], [26.0, 589.6363636363636], [27.0, 603.0], [28.0, 623.7142857142857], [29.0, 635.5555555555555], [30.0, 508.9060433557583]], "isOverall": false, "label": "HTTP请求配置", "isController": false}, {"data": [[29.851658039929426, 508.10084750583064]], "isOverall": false, "label": "HTTP请求配置-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 30.0, "title": "Time VS Threads"}},
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
        data : {"result": {"minY": 2591.7833333333333, "minX": 1.75038978E12, "maxY": 44189.0, "series": [{"data": [[1.7503899E12, 41181.0], [1.75038984E12, 44189.0], [1.75039002E12, 40911.38333333333], [1.75038996E12, 43730.75], [1.75038978E12, 7414.25], [1.75039008E12, 29077.866666666665]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.7503899E12, 14401.1], [1.75038984E12, 15452.7], [1.75039002E12, 14310.866666666667], [1.75038996E12, 15292.516666666666], [1.75038978E12, 2591.7833333333333], [1.75039008E12, 10166.25]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039008E12, "title": "Bytes Throughput Over Time"}},
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
        data: {"result": {"minY": 477.9106858054224, "minX": 1.75038978E12, "maxY": 570.247676767677, "series": [{"data": [[1.7503899E12, 513.5008556759816], [1.75038984E12, 477.9106858054224], [1.75039002E12, 512.8960964408731], [1.75038996E12, 483.4689766317488], [1.75038978E12, 533.1838351822503], [1.75039008E12, 570.247676767677]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039008E12, "title": "Response Time Over Time"}},
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
        data: {"result": {"minY": 477.70069112174315, "minX": 1.75038978E12, "maxY": 569.9058585858604, "series": [{"data": [[1.7503899E12, 513.2381631488894], [1.75038984E12, 477.70069112174315], [1.75039002E12, 512.6277267508609], [1.75038996E12, 483.24979854955757], [1.75038978E12, 533.0919175911249], [1.75039008E12, 569.9058585858604]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039008E12, "title": "Latencies Over Time"}},
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
        data: {"result": {"minY": 0.006060606060606091, "minX": 1.75038978E12, "maxY": 0.09667194928684626, "series": [{"data": [[1.7503899E12, 0.01026811180832862], [1.75038984E12, 0.009037745879851145], [1.75039002E12, 0.015499425947187161], [1.75038996E12, 0.008595218909481624], [1.75038978E12, 0.09667194928684626], [1.75039008E12, 0.006060606060606091]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039008E12, "title": "Connect Time Over Time"}},
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
        data: {"result": {"minY": 110.0, "minX": 1.75038978E12, "maxY": 1672.0, "series": [{"data": [[1.7503899E12, 1017.0], [1.75038984E12, 1149.0], [1.75039002E12, 1672.0], [1.75038996E12, 1111.0], [1.75038978E12, 913.0], [1.75039008E12, 1593.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.7503899E12, 587.0], [1.75038984E12, 594.0], [1.75039002E12, 700.0], [1.75038996E12, 556.0], [1.75038978E12, 650.0], [1.75039008E12, 741.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.7503899E12, 726.9299999999998], [1.75038984E12, 755.0], [1.75039002E12, 986.1500000000001], [1.75038996E12, 841.2799999999993], [1.75038978E12, 844.7999999999995], [1.75039008E12, 1086.4799999999996]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.7503899E12, 631.0], [1.75038984E12, 649.8499999999999], [1.75039002E12, 808.25], [1.75038996E12, 625.0], [1.75038978E12, 733.8], [1.75039008E12, 815.1999999999998]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.7503899E12, 168.0], [1.75038984E12, 130.0], [1.75039002E12, 155.0], [1.75038996E12, 150.0], [1.75038978E12, 110.0], [1.75039008E12, 135.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.7503899E12, 502.0], [1.75038984E12, 449.5], [1.75039002E12, 468.0], [1.75038996E12, 462.0], [1.75038978E12, 553.0], [1.75039008E12, 525.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039008E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
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
    data: {"result": {"minY": 148.0, "minX": 14.0, "maxY": 969.0, "series": [{"data": [[32.0, 841.0], [33.0, 961.0], [34.0, 437.0], [35.0, 810.5], [37.0, 774.0], [38.0, 726.0], [40.0, 683.0], [41.0, 748.0], [42.0, 695.0], [43.0, 696.5], [44.0, 634.0], [45.0, 623.0], [46.0, 601.0], [47.0, 606.0], [48.0, 632.0], [49.0, 640.0], [51.0, 574.0], [50.0, 555.0], [53.0, 572.5], [52.0, 575.0], [55.0, 543.0], [54.0, 542.5], [56.0, 536.0], [57.0, 505.0], [58.0, 519.0], [59.0, 505.0], [61.0, 487.0], [60.0, 496.0], [62.0, 479.0], [63.0, 476.0], [66.0, 456.0], [65.0, 456.0], [67.0, 453.0], [64.0, 463.0], [70.0, 421.0], [71.0, 418.0], [69.0, 429.0], [68.0, 448.0], [72.0, 417.0], [75.0, 410.0], [74.0, 417.0], [14.0, 148.0], [27.0, 969.0], [29.0, 723.0], [30.0, 668.0], [31.0, 864.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 75.0, "title": "Response Time Vs Request"}},
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
    data: {"result": {"minY": 148.0, "minX": 14.0, "maxY": 969.0, "series": [{"data": [[32.0, 840.5], [33.0, 961.0], [34.0, 437.0], [35.0, 810.0], [37.0, 774.0], [38.0, 726.0], [40.0, 682.5], [41.0, 747.0], [42.0, 693.0], [43.0, 696.5], [44.0, 634.0], [45.0, 622.5], [46.0, 600.5], [47.0, 605.0], [48.0, 631.0], [49.0, 640.0], [51.0, 574.0], [50.0, 549.0], [53.0, 572.0], [52.0, 575.0], [55.0, 543.0], [54.0, 542.5], [56.0, 536.0], [57.0, 505.0], [58.0, 519.0], [59.0, 505.0], [61.0, 486.0], [60.0, 496.0], [62.0, 478.0], [63.0, 476.0], [66.0, 456.0], [65.0, 456.0], [67.0, 453.0], [64.0, 462.0], [70.0, 421.0], [71.0, 418.0], [69.0, 428.0], [68.0, 447.0], [72.0, 417.0], [75.0, 409.0], [74.0, 417.0], [14.0, 148.0], [27.0, 969.0], [29.0, 723.0], [30.0, 667.5], [31.0, 864.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 75.0, "title": "Latencies Vs Request"}},
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
        data: {"result": {"minY": 11.016666666666667, "minX": 1.75038978E12, "maxY": 62.7, "series": [{"data": [[1.7503899E12, 58.43333333333333], [1.75038984E12, 62.7], [1.75039002E12, 58.06666666666667], [1.75038996E12, 62.05], [1.75038978E12, 11.016666666666667], [1.75039008E12, 40.75]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039008E12, "title": "Hits Per Second"}},
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
        data: {"result": {"minY": 10.516666666666667, "minX": 1.75038978E12, "maxY": 62.7, "series": [{"data": [[1.7503899E12, 58.43333333333333], [1.75038984E12, 62.7], [1.75039002E12, 58.06666666666667], [1.75038996E12, 62.05], [1.75038978E12, 10.516666666666667], [1.75039008E12, 41.25]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75039008E12, "title": "Codes Per Second"}},
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
        data: {"result": {"minY": 10.516666666666667, "minX": 1.75038978E12, "maxY": 62.7, "series": [{"data": [[1.7503899E12, 58.43333333333333], [1.75038984E12, 62.7], [1.75039002E12, 58.06666666666667], [1.75038996E12, 62.05], [1.75038978E12, 10.516666666666667], [1.75039008E12, 41.25]], "isOverall": false, "label": "HTTP请求配置-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039008E12, "title": "Transactions Per Second"}},
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
        data: {"result": {"minY": 10.516666666666667, "minX": 1.75038978E12, "maxY": 62.7, "series": [{"data": [[1.7503899E12, 58.43333333333333], [1.75038984E12, 62.7], [1.75039002E12, 58.06666666666667], [1.75038996E12, 62.05], [1.75038978E12, 10.516666666666667], [1.75039008E12, 41.25]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75039008E12, "title": "Total Transactions Per Second"}},
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

