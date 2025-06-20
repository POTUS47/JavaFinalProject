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
        data: {"result": {"minY": 115.0, "minX": 0.0, "maxY": 727.0, "series": [{"data": [[0.0, 115.0], [0.1, 136.0], [0.2, 161.0], [0.3, 231.0], [0.4, 268.0], [0.5, 296.0], [0.6, 302.0], [0.7, 305.0], [0.8, 309.0], [0.9, 311.0], [1.0, 314.0], [1.1, 316.0], [1.2, 316.0], [1.3, 318.0], [1.4, 318.0], [1.5, 319.0], [1.6, 320.0], [1.7, 321.0], [1.8, 322.0], [1.9, 323.0], [2.0, 323.0], [2.1, 324.0], [2.2, 325.0], [2.3, 325.0], [2.4, 326.0], [2.5, 327.0], [2.6, 327.0], [2.7, 328.0], [2.8, 328.0], [2.9, 329.0], [3.0, 329.0], [3.1, 330.0], [3.2, 331.0], [3.3, 331.0], [3.4, 331.0], [3.5, 332.0], [3.6, 332.0], [3.7, 332.0], [3.8, 333.0], [3.9, 333.0], [4.0, 333.0], [4.1, 334.0], [4.2, 334.0], [4.3, 335.0], [4.4, 335.0], [4.5, 335.0], [4.6, 335.0], [4.7, 336.0], [4.8, 336.0], [4.9, 336.0], [5.0, 337.0], [5.1, 337.0], [5.2, 337.0], [5.3, 338.0], [5.4, 338.0], [5.5, 338.0], [5.6, 339.0], [5.7, 339.0], [5.8, 339.0], [5.9, 339.0], [6.0, 339.0], [6.1, 340.0], [6.2, 340.0], [6.3, 340.0], [6.4, 341.0], [6.5, 341.0], [6.6, 341.0], [6.7, 341.0], [6.8, 341.0], [6.9, 342.0], [7.0, 342.0], [7.1, 342.0], [7.2, 342.0], [7.3, 342.0], [7.4, 343.0], [7.5, 343.0], [7.6, 343.0], [7.7, 343.0], [7.8, 343.0], [7.9, 343.0], [8.0, 344.0], [8.1, 344.0], [8.2, 344.0], [8.3, 344.0], [8.4, 344.0], [8.5, 345.0], [8.6, 345.0], [8.7, 345.0], [8.8, 345.0], [8.9, 345.0], [9.0, 346.0], [9.1, 346.0], [9.2, 346.0], [9.3, 346.0], [9.4, 346.0], [9.5, 346.0], [9.6, 347.0], [9.7, 347.0], [9.8, 347.0], [9.9, 347.0], [10.0, 347.0], [10.1, 347.0], [10.2, 348.0], [10.3, 348.0], [10.4, 348.0], [10.5, 348.0], [10.6, 348.0], [10.7, 348.0], [10.8, 348.0], [10.9, 349.0], [11.0, 349.0], [11.1, 349.0], [11.2, 349.0], [11.3, 349.0], [11.4, 349.0], [11.5, 350.0], [11.6, 350.0], [11.7, 350.0], [11.8, 350.0], [11.9, 350.0], [12.0, 350.0], [12.1, 350.0], [12.2, 351.0], [12.3, 351.0], [12.4, 351.0], [12.5, 351.0], [12.6, 351.0], [12.7, 351.0], [12.8, 351.0], [12.9, 352.0], [13.0, 352.0], [13.1, 352.0], [13.2, 352.0], [13.3, 352.0], [13.4, 352.0], [13.5, 352.0], [13.6, 352.0], [13.7, 352.0], [13.8, 353.0], [13.9, 353.0], [14.0, 353.0], [14.1, 353.0], [14.2, 353.0], [14.3, 353.0], [14.4, 353.0], [14.5, 354.0], [14.6, 354.0], [14.7, 354.0], [14.8, 354.0], [14.9, 354.0], [15.0, 354.0], [15.1, 354.0], [15.2, 354.0], [15.3, 354.0], [15.4, 355.0], [15.5, 355.0], [15.6, 355.0], [15.7, 355.0], [15.8, 355.0], [15.9, 355.0], [16.0, 355.0], [16.1, 355.0], [16.2, 355.0], [16.3, 356.0], [16.4, 356.0], [16.5, 356.0], [16.6, 356.0], [16.7, 356.0], [16.8, 356.0], [16.9, 356.0], [17.0, 356.0], [17.1, 357.0], [17.2, 357.0], [17.3, 357.0], [17.4, 357.0], [17.5, 357.0], [17.6, 357.0], [17.7, 357.0], [17.8, 357.0], [17.9, 357.0], [18.0, 357.0], [18.1, 358.0], [18.2, 358.0], [18.3, 358.0], [18.4, 358.0], [18.5, 358.0], [18.6, 358.0], [18.7, 358.0], [18.8, 358.0], [18.9, 358.0], [19.0, 358.0], [19.1, 358.0], [19.2, 359.0], [19.3, 359.0], [19.4, 359.0], [19.5, 359.0], [19.6, 359.0], [19.7, 359.0], [19.8, 359.0], [19.9, 359.0], [20.0, 359.0], [20.1, 359.0], [20.2, 359.0], [20.3, 360.0], [20.4, 360.0], [20.5, 360.0], [20.6, 360.0], [20.7, 360.0], [20.8, 360.0], [20.9, 360.0], [21.0, 360.0], [21.1, 360.0], [21.2, 361.0], [21.3, 361.0], [21.4, 361.0], [21.5, 361.0], [21.6, 361.0], [21.7, 361.0], [21.8, 361.0], [21.9, 361.0], [22.0, 361.0], [22.1, 361.0], [22.2, 361.0], [22.3, 362.0], [22.4, 362.0], [22.5, 362.0], [22.6, 362.0], [22.7, 362.0], [22.8, 362.0], [22.9, 362.0], [23.0, 362.0], [23.1, 362.0], [23.2, 362.0], [23.3, 362.0], [23.4, 362.0], [23.5, 363.0], [23.6, 363.0], [23.7, 363.0], [23.8, 363.0], [23.9, 363.0], [24.0, 363.0], [24.1, 363.0], [24.2, 363.0], [24.3, 363.0], [24.4, 363.0], [24.5, 363.0], [24.6, 363.0], [24.7, 364.0], [24.8, 364.0], [24.9, 364.0], [25.0, 364.0], [25.1, 364.0], [25.2, 364.0], [25.3, 364.0], [25.4, 364.0], [25.5, 364.0], [25.6, 364.0], [25.7, 364.0], [25.8, 364.0], [25.9, 365.0], [26.0, 365.0], [26.1, 365.0], [26.2, 365.0], [26.3, 365.0], [26.4, 365.0], [26.5, 365.0], [26.6, 365.0], [26.7, 365.0], [26.8, 365.0], [26.9, 365.0], [27.0, 365.0], [27.1, 366.0], [27.2, 366.0], [27.3, 366.0], [27.4, 366.0], [27.5, 366.0], [27.6, 366.0], [27.7, 366.0], [27.8, 366.0], [27.9, 366.0], [28.0, 366.0], [28.1, 366.0], [28.2, 366.0], [28.3, 367.0], [28.4, 367.0], [28.5, 367.0], [28.6, 367.0], [28.7, 367.0], [28.8, 367.0], [28.9, 367.0], [29.0, 367.0], [29.1, 367.0], [29.2, 367.0], [29.3, 367.0], [29.4, 367.0], [29.5, 367.0], [29.6, 368.0], [29.7, 368.0], [29.8, 368.0], [29.9, 368.0], [30.0, 368.0], [30.1, 368.0], [30.2, 368.0], [30.3, 368.0], [30.4, 368.0], [30.5, 368.0], [30.6, 368.0], [30.7, 368.0], [30.8, 368.0], [30.9, 369.0], [31.0, 369.0], [31.1, 369.0], [31.2, 369.0], [31.3, 369.0], [31.4, 369.0], [31.5, 369.0], [31.6, 369.0], [31.7, 369.0], [31.8, 369.0], [31.9, 369.0], [32.0, 369.0], [32.1, 370.0], [32.2, 370.0], [32.3, 370.0], [32.4, 370.0], [32.5, 370.0], [32.6, 370.0], [32.7, 370.0], [32.8, 370.0], [32.9, 370.0], [33.0, 370.0], [33.1, 370.0], [33.2, 370.0], [33.3, 370.0], [33.4, 370.0], [33.5, 371.0], [33.6, 371.0], [33.7, 371.0], [33.8, 371.0], [33.9, 371.0], [34.0, 371.0], [34.1, 371.0], [34.2, 371.0], [34.3, 371.0], [34.4, 371.0], [34.5, 371.0], [34.6, 371.0], [34.7, 371.0], [34.8, 371.0], [34.9, 372.0], [35.0, 372.0], [35.1, 372.0], [35.2, 372.0], [35.3, 372.0], [35.4, 372.0], [35.5, 372.0], [35.6, 372.0], [35.7, 372.0], [35.8, 372.0], [35.9, 372.0], [36.0, 372.0], [36.1, 372.0], [36.2, 372.0], [36.3, 373.0], [36.4, 373.0], [36.5, 373.0], [36.6, 373.0], [36.7, 373.0], [36.8, 373.0], [36.9, 373.0], [37.0, 373.0], [37.1, 373.0], [37.2, 373.0], [37.3, 373.0], [37.4, 373.0], [37.5, 373.0], [37.6, 374.0], [37.7, 374.0], [37.8, 374.0], [37.9, 374.0], [38.0, 374.0], [38.1, 374.0], [38.2, 374.0], [38.3, 374.0], [38.4, 374.0], [38.5, 374.0], [38.6, 374.0], [38.7, 374.0], [38.8, 374.0], [38.9, 375.0], [39.0, 375.0], [39.1, 375.0], [39.2, 375.0], [39.3, 375.0], [39.4, 375.0], [39.5, 375.0], [39.6, 375.0], [39.7, 375.0], [39.8, 375.0], [39.9, 375.0], [40.0, 375.0], [40.1, 375.0], [40.2, 375.0], [40.3, 376.0], [40.4, 376.0], [40.5, 376.0], [40.6, 376.0], [40.7, 376.0], [40.8, 376.0], [40.9, 376.0], [41.0, 376.0], [41.1, 376.0], [41.2, 376.0], [41.3, 376.0], [41.4, 376.0], [41.5, 376.0], [41.6, 376.0], [41.7, 377.0], [41.8, 377.0], [41.9, 377.0], [42.0, 377.0], [42.1, 377.0], [42.2, 377.0], [42.3, 377.0], [42.4, 377.0], [42.5, 377.0], [42.6, 377.0], [42.7, 377.0], [42.8, 377.0], [42.9, 377.0], [43.0, 377.0], [43.1, 377.0], [43.2, 378.0], [43.3, 378.0], [43.4, 378.0], [43.5, 378.0], [43.6, 378.0], [43.7, 378.0], [43.8, 378.0], [43.9, 378.0], [44.0, 378.0], [44.1, 378.0], [44.2, 378.0], [44.3, 378.0], [44.4, 378.0], [44.5, 378.0], [44.6, 378.0], [44.7, 379.0], [44.8, 379.0], [44.9, 379.0], [45.0, 379.0], [45.1, 379.0], [45.2, 379.0], [45.3, 379.0], [45.4, 379.0], [45.5, 379.0], [45.6, 379.0], [45.7, 379.0], [45.8, 379.0], [45.9, 379.0], [46.0, 379.0], [46.1, 380.0], [46.2, 380.0], [46.3, 380.0], [46.4, 380.0], [46.5, 380.0], [46.6, 380.0], [46.7, 380.0], [46.8, 380.0], [46.9, 380.0], [47.0, 380.0], [47.1, 380.0], [47.2, 380.0], [47.3, 380.0], [47.4, 380.0], [47.5, 381.0], [47.6, 381.0], [47.7, 381.0], [47.8, 381.0], [47.9, 381.0], [48.0, 381.0], [48.1, 381.0], [48.2, 381.0], [48.3, 381.0], [48.4, 381.0], [48.5, 381.0], [48.6, 381.0], [48.7, 381.0], [48.8, 382.0], [48.9, 382.0], [49.0, 382.0], [49.1, 382.0], [49.2, 382.0], [49.3, 382.0], [49.4, 382.0], [49.5, 382.0], [49.6, 382.0], [49.7, 382.0], [49.8, 382.0], [49.9, 382.0], [50.0, 382.0], [50.1, 382.0], [50.2, 382.0], [50.3, 383.0], [50.4, 383.0], [50.5, 383.0], [50.6, 383.0], [50.7, 383.0], [50.8, 383.0], [50.9, 383.0], [51.0, 383.0], [51.1, 383.0], [51.2, 383.0], [51.3, 383.0], [51.4, 383.0], [51.5, 383.0], [51.6, 383.0], [51.7, 383.0], [51.8, 384.0], [51.9, 384.0], [52.0, 384.0], [52.1, 384.0], [52.2, 384.0], [52.3, 384.0], [52.4, 384.0], [52.5, 384.0], [52.6, 384.0], [52.7, 384.0], [52.8, 384.0], [52.9, 384.0], [53.0, 384.0], [53.1, 385.0], [53.2, 385.0], [53.3, 385.0], [53.4, 385.0], [53.5, 385.0], [53.6, 385.0], [53.7, 385.0], [53.8, 385.0], [53.9, 385.0], [54.0, 385.0], [54.1, 385.0], [54.2, 385.0], [54.3, 385.0], [54.4, 386.0], [54.5, 386.0], [54.6, 386.0], [54.7, 386.0], [54.8, 386.0], [54.9, 386.0], [55.0, 386.0], [55.1, 386.0], [55.2, 386.0], [55.3, 386.0], [55.4, 386.0], [55.5, 386.0], [55.6, 387.0], [55.7, 387.0], [55.8, 387.0], [55.9, 387.0], [56.0, 387.0], [56.1, 387.0], [56.2, 387.0], [56.3, 387.0], [56.4, 387.0], [56.5, 387.0], [56.6, 387.0], [56.7, 387.0], [56.8, 388.0], [56.9, 388.0], [57.0, 388.0], [57.1, 388.0], [57.2, 388.0], [57.3, 388.0], [57.4, 388.0], [57.5, 388.0], [57.6, 388.0], [57.7, 388.0], [57.8, 388.0], [57.9, 388.0], [58.0, 389.0], [58.1, 389.0], [58.2, 389.0], [58.3, 389.0], [58.4, 389.0], [58.5, 389.0], [58.6, 389.0], [58.7, 389.0], [58.8, 389.0], [58.9, 389.0], [59.0, 389.0], [59.1, 390.0], [59.2, 390.0], [59.3, 390.0], [59.4, 390.0], [59.5, 390.0], [59.6, 390.0], [59.7, 390.0], [59.8, 390.0], [59.9, 390.0], [60.0, 390.0], [60.1, 390.0], [60.2, 390.0], [60.3, 390.0], [60.4, 391.0], [60.5, 391.0], [60.6, 391.0], [60.7, 391.0], [60.8, 391.0], [60.9, 391.0], [61.0, 391.0], [61.1, 391.0], [61.2, 391.0], [61.3, 391.0], [61.4, 391.0], [61.5, 391.0], [61.6, 392.0], [61.7, 392.0], [61.8, 392.0], [61.9, 392.0], [62.0, 392.0], [62.1, 392.0], [62.2, 392.0], [62.3, 392.0], [62.4, 392.0], [62.5, 392.0], [62.6, 392.0], [62.7, 392.0], [62.8, 393.0], [62.9, 393.0], [63.0, 393.0], [63.1, 393.0], [63.2, 393.0], [63.3, 393.0], [63.4, 393.0], [63.5, 393.0], [63.6, 393.0], [63.7, 393.0], [63.8, 393.0], [63.9, 393.0], [64.0, 394.0], [64.1, 394.0], [64.2, 394.0], [64.3, 394.0], [64.4, 394.0], [64.5, 394.0], [64.6, 394.0], [64.7, 394.0], [64.8, 394.0], [64.9, 394.0], [65.0, 394.0], [65.1, 395.0], [65.2, 395.0], [65.3, 395.0], [65.4, 395.0], [65.5, 395.0], [65.6, 395.0], [65.7, 395.0], [65.8, 395.0], [65.9, 395.0], [66.0, 396.0], [66.1, 396.0], [66.2, 396.0], [66.3, 396.0], [66.4, 396.0], [66.5, 396.0], [66.6, 396.0], [66.7, 396.0], [66.8, 396.0], [66.9, 396.0], [67.0, 396.0], [67.1, 396.0], [67.2, 397.0], [67.3, 397.0], [67.4, 397.0], [67.5, 397.0], [67.6, 397.0], [67.7, 397.0], [67.8, 397.0], [67.9, 397.0], [68.0, 397.0], [68.1, 397.0], [68.2, 398.0], [68.3, 398.0], [68.4, 398.0], [68.5, 398.0], [68.6, 398.0], [68.7, 398.0], [68.8, 398.0], [68.9, 398.0], [69.0, 398.0], [69.1, 398.0], [69.2, 398.0], [69.3, 399.0], [69.4, 399.0], [69.5, 399.0], [69.6, 399.0], [69.7, 399.0], [69.8, 399.0], [69.9, 399.0], [70.0, 399.0], [70.1, 399.0], [70.2, 400.0], [70.3, 400.0], [70.4, 400.0], [70.5, 400.0], [70.6, 400.0], [70.7, 400.0], [70.8, 400.0], [70.9, 400.0], [71.0, 400.0], [71.1, 400.0], [71.2, 401.0], [71.3, 401.0], [71.4, 401.0], [71.5, 401.0], [71.6, 401.0], [71.7, 401.0], [71.8, 401.0], [71.9, 401.0], [72.0, 401.0], [72.1, 401.0], [72.2, 402.0], [72.3, 402.0], [72.4, 402.0], [72.5, 402.0], [72.6, 402.0], [72.7, 402.0], [72.8, 402.0], [72.9, 402.0], [73.0, 402.0], [73.1, 403.0], [73.2, 403.0], [73.3, 403.0], [73.4, 403.0], [73.5, 403.0], [73.6, 403.0], [73.7, 403.0], [73.8, 403.0], [73.9, 403.0], [74.0, 403.0], [74.1, 404.0], [74.2, 404.0], [74.3, 404.0], [74.4, 404.0], [74.5, 404.0], [74.6, 404.0], [74.7, 404.0], [74.8, 404.0], [74.9, 404.0], [75.0, 405.0], [75.1, 405.0], [75.2, 405.0], [75.3, 405.0], [75.4, 405.0], [75.5, 405.0], [75.6, 405.0], [75.7, 405.0], [75.8, 406.0], [75.9, 406.0], [76.0, 406.0], [76.1, 406.0], [76.2, 406.0], [76.3, 406.0], [76.4, 406.0], [76.5, 407.0], [76.6, 407.0], [76.7, 407.0], [76.8, 407.0], [76.9, 407.0], [77.0, 407.0], [77.1, 407.0], [77.2, 407.0], [77.3, 408.0], [77.4, 408.0], [77.5, 408.0], [77.6, 408.0], [77.7, 408.0], [77.8, 408.0], [77.9, 408.0], [78.0, 409.0], [78.1, 409.0], [78.2, 409.0], [78.3, 409.0], [78.4, 409.0], [78.5, 409.0], [78.6, 409.0], [78.7, 409.0], [78.8, 410.0], [78.9, 410.0], [79.0, 410.0], [79.1, 410.0], [79.2, 410.0], [79.3, 410.0], [79.4, 410.0], [79.5, 410.0], [79.6, 411.0], [79.7, 411.0], [79.8, 411.0], [79.9, 411.0], [80.0, 411.0], [80.1, 411.0], [80.2, 412.0], [80.3, 412.0], [80.4, 412.0], [80.5, 412.0], [80.6, 412.0], [80.7, 412.0], [80.8, 412.0], [80.9, 413.0], [81.0, 413.0], [81.1, 413.0], [81.2, 413.0], [81.3, 413.0], [81.4, 413.0], [81.5, 413.0], [81.6, 413.0], [81.7, 414.0], [81.8, 414.0], [81.9, 414.0], [82.0, 414.0], [82.1, 414.0], [82.2, 414.0], [82.3, 415.0], [82.4, 415.0], [82.5, 415.0], [82.6, 415.0], [82.7, 415.0], [82.8, 415.0], [82.9, 415.0], [83.0, 416.0], [83.1, 416.0], [83.2, 416.0], [83.3, 416.0], [83.4, 416.0], [83.5, 416.0], [83.6, 416.0], [83.7, 417.0], [83.8, 417.0], [83.9, 417.0], [84.0, 417.0], [84.1, 417.0], [84.2, 417.0], [84.3, 418.0], [84.4, 418.0], [84.5, 418.0], [84.6, 418.0], [84.7, 418.0], [84.8, 419.0], [84.9, 419.0], [85.0, 419.0], [85.1, 419.0], [85.2, 419.0], [85.3, 420.0], [85.4, 420.0], [85.5, 420.0], [85.6, 420.0], [85.7, 420.0], [85.8, 420.0], [85.9, 421.0], [86.0, 421.0], [86.1, 421.0], [86.2, 421.0], [86.3, 421.0], [86.4, 422.0], [86.5, 422.0], [86.6, 422.0], [86.7, 422.0], [86.8, 423.0], [86.9, 423.0], [87.0, 423.0], [87.1, 423.0], [87.2, 423.0], [87.3, 424.0], [87.4, 424.0], [87.5, 424.0], [87.6, 424.0], [87.7, 424.0], [87.8, 425.0], [87.9, 425.0], [88.0, 425.0], [88.1, 425.0], [88.2, 426.0], [88.3, 426.0], [88.4, 426.0], [88.5, 426.0], [88.6, 427.0], [88.7, 427.0], [88.8, 427.0], [88.9, 428.0], [89.0, 428.0], [89.1, 428.0], [89.2, 428.0], [89.3, 429.0], [89.4, 429.0], [89.5, 429.0], [89.6, 430.0], [89.7, 430.0], [89.8, 430.0], [89.9, 431.0], [90.0, 431.0], [90.1, 431.0], [90.2, 432.0], [90.3, 432.0], [90.4, 432.0], [90.5, 432.0], [90.6, 433.0], [90.7, 433.0], [90.8, 433.0], [90.9, 434.0], [91.0, 434.0], [91.1, 434.0], [91.2, 434.0], [91.3, 435.0], [91.4, 435.0], [91.5, 436.0], [91.6, 436.0], [91.7, 436.0], [91.8, 437.0], [91.9, 437.0], [92.0, 438.0], [92.1, 438.0], [92.2, 438.0], [92.3, 439.0], [92.4, 439.0], [92.5, 440.0], [92.6, 440.0], [92.7, 440.0], [92.8, 441.0], [92.9, 441.0], [93.0, 442.0], [93.1, 442.0], [93.2, 443.0], [93.3, 443.0], [93.4, 444.0], [93.5, 444.0], [93.6, 445.0], [93.7, 446.0], [93.8, 446.0], [93.9, 447.0], [94.0, 447.0], [94.1, 448.0], [94.2, 448.0], [94.3, 449.0], [94.4, 450.0], [94.5, 451.0], [94.6, 451.0], [94.7, 452.0], [94.8, 453.0], [94.9, 454.0], [95.0, 454.0], [95.1, 455.0], [95.2, 456.0], [95.3, 457.0], [95.4, 457.0], [95.5, 459.0], [95.6, 459.0], [95.7, 460.0], [95.8, 461.0], [95.9, 462.0], [96.0, 464.0], [96.1, 465.0], [96.2, 466.0], [96.3, 467.0], [96.4, 468.0], [96.5, 469.0], [96.6, 471.0], [96.7, 473.0], [96.8, 474.0], [96.9, 476.0], [97.0, 478.0], [97.1, 481.0], [97.2, 483.0], [97.3, 485.0], [97.4, 487.0], [97.5, 490.0], [97.6, 493.0], [97.7, 496.0], [97.8, 499.0], [97.9, 501.0], [98.0, 504.0], [98.1, 508.0], [98.2, 510.0], [98.3, 512.0], [98.4, 516.0], [98.5, 521.0], [98.6, 525.0], [98.7, 531.0], [98.8, 537.0], [98.9, 543.0], [99.0, 553.0], [99.1, 559.0], [99.2, 568.0], [99.3, 575.0], [99.4, 586.0], [99.5, 595.0], [99.6, 609.0], [99.7, 618.0], [99.8, 641.0], [99.9, 674.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
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
        data: {"result": {"minY": 12.0, "minX": 100.0, "maxY": 13359.0, "series": [{"data": [[300.0, 13359.0], [600.0, 77.0], [700.0, 12.0], [100.0, 51.0], [200.0, 55.0], [400.0, 5310.0], [500.0, 330.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 700.0, "title": "Response Time Distribution"}},
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
        data: {"result": {"minY": 405.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 18789.0, "series": [{"data": [[0.0, 18789.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 405.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 1.0, "title": "Synthetic Response Times Distribution"}},
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
        data: {"result": {"minY": 22.92490613266583, "minX": 1.75038906E12, "maxY": 25.0, "series": [{"data": [[1.75038924E12, 25.0], [1.75038936E12, 24.89354151880766], [1.75038906E12, 22.92490613266583], [1.75038918E12, 25.0], [1.75038912E12, 25.0], [1.7503893E12, 25.0]], "isOverall": false, "label": "线程组", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75038936E12, "title": "Active Threads Over Time"}},
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
        data: {"result": {"minY": 151.27272727272725, "minX": 1.0, "maxY": 579.75, "series": [{"data": [[2.0, 204.0], [3.0, 376.0], [4.0, 275.5], [5.0, 177.5], [6.0, 151.27272727272725], [7.0, 186.33333333333331], [8.0, 207.9], [9.0, 232.28571428571428], [10.0, 255.29999999999998], [11.0, 283.57142857142856], [12.0, 294.75], [13.0, 337.0], [14.0, 399.0], [15.0, 391.5], [16.0, 431.5], [1.0, 344.0], [17.0, 479.42857142857144], [18.0, 487.75], [19.0, 462.66666666666674], [20.0, 472.57142857142856], [21.0, 506.5714285714286], [22.0, 553.0], [23.0, 568.7142857142857], [24.0, 579.75], [25.0, 387.88556560134606]], "isOverall": false, "label": "HTTP请求配置", "isController": false}, {"data": [[24.897988954881672, 387.6218088986125]], "isOverall": false, "label": "HTTP请求配置-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 25.0, "title": "Time VS Threads"}},
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
        data : {"result": {"minY": 3281.7833333333333, "minX": 1.75038906E12, "maxY": 48558.55, "series": [{"data": [[1.75038924E12, 45324.4], [1.75038936E12, 33099.416666666664], [1.75038906E12, 9388.25], [1.75038918E12, 44312.45], [1.75038912E12, 48558.55], [1.7503893E12, 44761.85]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.75038924E12, 15851.116666666667], [1.75038936E12, 11575.233333333334], [1.75038906E12, 3281.7833333333333], [1.75038918E12, 15497.983333333334], [1.75038912E12, 16980.733333333334], [1.7503893E12, 15653.85]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75038936E12, "title": "Bytes Throughput Over Time"}},
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
        data: {"result": {"minY": 362.58635703918685, "minX": 1.75038906E12, "maxY": 438.11639549436796, "series": [{"data": [[1.75038924E12, 388.8626587198748], [1.75038936E12, 387.7058197303053], [1.75038906E12, 438.11639549436796], [1.75038918E12, 397.33130135170904], [1.75038912E12, 362.58635703918685], [1.7503893E12, 393.26134872736804]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75038936E12, "title": "Response Time Over Time"}},
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
        data: {"result": {"minY": 362.42718916303846, "minX": 1.75038906E12, "maxY": 438.0162703379225, "series": [{"data": [[1.75038924E12, 388.6633842964499], [1.75038936E12, 387.5053229240594], [1.75038906E12, 438.0162703379225], [1.75038918E12, 397.1288099655443], [1.75038912E12, 362.42718916303846], [1.7503893E12, 393.0519548674898]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75038936E12, "title": "Latencies Over Time"}},
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
        data: {"result": {"minY": 0.0089501693275278, "minX": 1.75038906E12, "maxY": 0.06257822277847293, "series": [{"data": [[1.75038924E12, 0.011401917595231919], [1.75038936E12, 0.01029098651525907], [1.75038906E12, 0.06257822277847293], [1.75038918E12, 0.012456930824277762], [1.75038912E12, 0.0089501693275278], [1.7503893E12, 0.009971136184728448]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75038936E12, "title": "Connect Time Over Time"}},
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
        data: {"result": {"minY": 115.0, "minX": 1.75038906E12, "maxY": 727.0, "series": [{"data": [[1.75038924E12, 646.0], [1.75038936E12, 646.0], [1.75038906E12, 727.0], [1.75038918E12, 704.0], [1.75038912E12, 658.0], [1.7503893E12, 662.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.75038924E12, 423.0], [1.75038936E12, 422.0], [1.75038906E12, 571.0], [1.75038918E12, 443.5999999999999], [1.75038912E12, 399.0], [1.7503893E12, 431.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.75038924E12, 485.8000000000002], [1.75038936E12, 480.2399999999998], [1.75038906E12, 708.0], [1.75038918E12, 517.0], [1.75038912E12, 457.0], [1.7503893E12, 533.7600000000002]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.75038924E12, 438.0], [1.75038936E12, 437.0], [1.75038906E12, 619.0], [1.75038918E12, 468.0], [1.75038912E12, 414.0], [1.7503893E12, 449.0]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.75038924E12, 138.0], [1.75038936E12, 148.0], [1.75038906E12, 115.0], [1.75038918E12, 133.0], [1.75038912E12, 147.0], [1.7503893E12, 136.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.75038924E12, 386.0], [1.75038936E12, 383.0], [1.75038906E12, 433.0], [1.75038918E12, 391.0], [1.75038912E12, 359.0], [1.7503893E12, 388.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75038936E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
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
    data: {"result": {"minY": 148.0, "minX": 3.0, "maxY": 639.5, "series": [{"data": [[32.0, 445.5], [33.0, 540.0], [34.0, 265.5], [37.0, 148.0], [36.0, 639.5], [45.0, 548.0], [46.0, 524.0], [47.0, 375.0], [3.0, 157.0], [49.0, 510.5], [54.0, 483.0], [55.0, 460.0], [56.0, 461.0], [57.0, 407.0], [59.0, 414.0], [58.0, 413.5], [61.0, 405.0], [60.0, 409.0], [62.0, 398.0], [63.0, 393.0], [64.0, 387.0], [65.0, 383.0], [66.0, 379.0], [67.0, 376.0], [69.0, 363.0], [71.0, 351.0], [68.0, 369.5], [70.0, 360.0], [73.0, 345.0], [74.0, 352.5], [72.0, 343.0], [75.0, 339.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 75.0, "title": "Response Time Vs Request"}},
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
    data: {"result": {"minY": 148.0, "minX": 3.0, "maxY": 639.5, "series": [{"data": [[32.0, 445.0], [33.0, 540.0], [34.0, 265.5], [37.0, 148.0], [36.0, 639.5], [45.0, 548.0], [46.0, 524.0], [47.0, 375.0], [3.0, 157.0], [49.0, 510.5], [54.0, 483.0], [55.0, 460.0], [56.0, 461.0], [57.0, 407.0], [59.0, 414.0], [58.0, 413.5], [61.0, 405.0], [60.0, 408.5], [62.0, 398.0], [63.0, 392.0], [64.0, 387.0], [65.0, 383.0], [66.0, 379.0], [67.0, 375.0], [69.0, 363.0], [71.0, 351.0], [68.0, 369.0], [70.0, 360.0], [73.0, 345.0], [74.0, 352.0], [72.0, 343.0], [75.0, 338.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 75.0, "title": "Latencies Vs Request"}},
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
        data: {"result": {"minY": 13.733333333333333, "minX": 1.75038906E12, "maxY": 68.9, "series": [{"data": [[1.75038924E12, 64.31666666666666], [1.75038936E12, 46.55], [1.75038906E12, 13.733333333333333], [1.75038918E12, 62.88333333333333], [1.75038912E12, 68.9], [1.7503893E12, 63.516666666666666]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75038936E12, "title": "Hits Per Second"}},
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
        data: {"result": {"minY": 13.316666666666666, "minX": 1.75038906E12, "maxY": 68.9, "series": [{"data": [[1.75038924E12, 64.31666666666666], [1.75038936E12, 46.96666666666667], [1.75038906E12, 13.316666666666666], [1.75038918E12, 62.88333333333333], [1.75038912E12, 68.9], [1.7503893E12, 63.516666666666666]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75038936E12, "title": "Codes Per Second"}},
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
        data: {"result": {"minY": 13.316666666666666, "minX": 1.75038906E12, "maxY": 68.9, "series": [{"data": [[1.75038924E12, 64.31666666666666], [1.75038936E12, 46.96666666666667], [1.75038906E12, 13.316666666666666], [1.75038918E12, 62.88333333333333], [1.75038912E12, 68.9], [1.7503893E12, 63.516666666666666]], "isOverall": false, "label": "HTTP请求配置-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75038936E12, "title": "Transactions Per Second"}},
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
        data: {"result": {"minY": 13.316666666666666, "minX": 1.75038906E12, "maxY": 68.9, "series": [{"data": [[1.75038924E12, 64.31666666666666], [1.75038936E12, 46.96666666666667], [1.75038906E12, 13.316666666666666], [1.75038918E12, 62.88333333333333], [1.75038912E12, 68.9], [1.7503893E12, 63.516666666666666]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75038936E12, "title": "Total Transactions Per Second"}},
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

