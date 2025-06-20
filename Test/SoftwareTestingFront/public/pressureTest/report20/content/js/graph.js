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
        data: {"result": {"minY": 130.0, "minX": 0.0, "maxY": 679.0, "series": [{"data": [[0.0, 130.0], [0.1, 162.0], [0.2, 201.0], [0.3, 243.0], [0.4, 248.0], [0.5, 250.0], [0.6, 252.0], [0.7, 253.0], [0.8, 254.0], [0.9, 255.0], [1.0, 256.0], [1.1, 256.0], [1.2, 257.0], [1.3, 257.0], [1.4, 258.0], [1.5, 258.0], [1.6, 259.0], [1.7, 259.0], [1.8, 260.0], [1.9, 260.0], [2.0, 260.0], [2.1, 261.0], [2.2, 261.0], [2.3, 261.0], [2.4, 262.0], [2.5, 262.0], [2.6, 262.0], [2.7, 263.0], [2.8, 263.0], [2.9, 264.0], [3.0, 264.0], [3.1, 264.0], [3.2, 264.0], [3.3, 265.0], [3.4, 265.0], [3.5, 265.0], [3.6, 266.0], [3.7, 266.0], [3.8, 266.0], [3.9, 266.0], [4.0, 266.0], [4.1, 267.0], [4.2, 267.0], [4.3, 267.0], [4.4, 267.0], [4.5, 268.0], [4.6, 268.0], [4.7, 268.0], [4.8, 268.0], [4.9, 268.0], [5.0, 268.0], [5.1, 269.0], [5.2, 269.0], [5.3, 269.0], [5.4, 269.0], [5.5, 269.0], [5.6, 270.0], [5.7, 270.0], [5.8, 270.0], [5.9, 270.0], [6.0, 270.0], [6.1, 270.0], [6.2, 271.0], [6.3, 271.0], [6.4, 271.0], [6.5, 271.0], [6.6, 271.0], [6.7, 271.0], [6.8, 272.0], [6.9, 272.0], [7.0, 272.0], [7.1, 272.0], [7.2, 272.0], [7.3, 272.0], [7.4, 272.0], [7.5, 273.0], [7.6, 273.0], [7.7, 273.0], [7.8, 273.0], [7.9, 273.0], [8.0, 273.0], [8.1, 274.0], [8.2, 274.0], [8.3, 274.0], [8.4, 274.0], [8.5, 274.0], [8.6, 274.0], [8.7, 274.0], [8.8, 274.0], [8.9, 275.0], [9.0, 275.0], [9.1, 275.0], [9.2, 275.0], [9.3, 275.0], [9.4, 275.0], [9.5, 275.0], [9.6, 275.0], [9.7, 276.0], [9.8, 276.0], [9.9, 276.0], [10.0, 276.0], [10.1, 276.0], [10.2, 276.0], [10.3, 276.0], [10.4, 276.0], [10.5, 277.0], [10.6, 277.0], [10.7, 277.0], [10.8, 277.0], [10.9, 277.0], [11.0, 277.0], [11.1, 277.0], [11.2, 277.0], [11.3, 278.0], [11.4, 278.0], [11.5, 278.0], [11.6, 278.0], [11.7, 278.0], [11.8, 278.0], [11.9, 278.0], [12.0, 278.0], [12.1, 278.0], [12.2, 279.0], [12.3, 279.0], [12.4, 279.0], [12.5, 279.0], [12.6, 279.0], [12.7, 279.0], [12.8, 279.0], [12.9, 279.0], [13.0, 279.0], [13.1, 280.0], [13.2, 280.0], [13.3, 280.0], [13.4, 280.0], [13.5, 280.0], [13.6, 280.0], [13.7, 280.0], [13.8, 280.0], [13.9, 280.0], [14.0, 280.0], [14.1, 280.0], [14.2, 281.0], [14.3, 281.0], [14.4, 281.0], [14.5, 281.0], [14.6, 281.0], [14.7, 281.0], [14.8, 281.0], [14.9, 281.0], [15.0, 281.0], [15.1, 281.0], [15.2, 281.0], [15.3, 281.0], [15.4, 282.0], [15.5, 282.0], [15.6, 282.0], [15.7, 282.0], [15.8, 282.0], [15.9, 282.0], [16.0, 282.0], [16.1, 282.0], [16.2, 282.0], [16.3, 282.0], [16.4, 282.0], [16.5, 283.0], [16.6, 283.0], [16.7, 283.0], [16.8, 283.0], [16.9, 283.0], [17.0, 283.0], [17.1, 283.0], [17.2, 283.0], [17.3, 283.0], [17.4, 283.0], [17.5, 283.0], [17.6, 283.0], [17.7, 284.0], [17.8, 284.0], [17.9, 284.0], [18.0, 284.0], [18.1, 284.0], [18.2, 284.0], [18.3, 284.0], [18.4, 284.0], [18.5, 284.0], [18.6, 284.0], [18.7, 284.0], [18.8, 285.0], [18.9, 285.0], [19.0, 285.0], [19.1, 285.0], [19.2, 285.0], [19.3, 285.0], [19.4, 285.0], [19.5, 285.0], [19.6, 285.0], [19.7, 285.0], [19.8, 285.0], [19.9, 285.0], [20.0, 285.0], [20.1, 286.0], [20.2, 286.0], [20.3, 286.0], [20.4, 286.0], [20.5, 286.0], [20.6, 286.0], [20.7, 286.0], [20.8, 286.0], [20.9, 286.0], [21.0, 286.0], [21.1, 286.0], [21.2, 286.0], [21.3, 286.0], [21.4, 286.0], [21.5, 287.0], [21.6, 287.0], [21.7, 287.0], [21.8, 287.0], [21.9, 287.0], [22.0, 287.0], [22.1, 287.0], [22.2, 287.0], [22.3, 287.0], [22.4, 287.0], [22.5, 287.0], [22.6, 287.0], [22.7, 287.0], [22.8, 287.0], [22.9, 287.0], [23.0, 287.0], [23.1, 288.0], [23.2, 288.0], [23.3, 288.0], [23.4, 288.0], [23.5, 288.0], [23.6, 288.0], [23.7, 288.0], [23.8, 288.0], [23.9, 288.0], [24.0, 288.0], [24.1, 288.0], [24.2, 288.0], [24.3, 288.0], [24.4, 288.0], [24.5, 289.0], [24.6, 289.0], [24.7, 289.0], [24.8, 289.0], [24.9, 289.0], [25.0, 289.0], [25.1, 289.0], [25.2, 289.0], [25.3, 289.0], [25.4, 289.0], [25.5, 289.0], [25.6, 289.0], [25.7, 289.0], [25.8, 289.0], [25.9, 289.0], [26.0, 289.0], [26.1, 289.0], [26.2, 290.0], [26.3, 290.0], [26.4, 290.0], [26.5, 290.0], [26.6, 290.0], [26.7, 290.0], [26.8, 290.0], [26.9, 290.0], [27.0, 290.0], [27.1, 290.0], [27.2, 290.0], [27.3, 290.0], [27.4, 290.0], [27.5, 290.0], [27.6, 290.0], [27.7, 291.0], [27.8, 291.0], [27.9, 291.0], [28.0, 291.0], [28.1, 291.0], [28.2, 291.0], [28.3, 291.0], [28.4, 291.0], [28.5, 291.0], [28.6, 291.0], [28.7, 291.0], [28.8, 291.0], [28.9, 291.0], [29.0, 291.0], [29.1, 291.0], [29.2, 291.0], [29.3, 292.0], [29.4, 292.0], [29.5, 292.0], [29.6, 292.0], [29.7, 292.0], [29.8, 292.0], [29.9, 292.0], [30.0, 292.0], [30.1, 292.0], [30.2, 292.0], [30.3, 292.0], [30.4, 292.0], [30.5, 292.0], [30.6, 292.0], [30.7, 292.0], [30.8, 292.0], [30.9, 293.0], [31.0, 293.0], [31.1, 293.0], [31.2, 293.0], [31.3, 293.0], [31.4, 293.0], [31.5, 293.0], [31.6, 293.0], [31.7, 293.0], [31.8, 293.0], [31.9, 293.0], [32.0, 293.0], [32.1, 293.0], [32.2, 293.0], [32.3, 293.0], [32.4, 293.0], [32.5, 294.0], [32.6, 294.0], [32.7, 294.0], [32.8, 294.0], [32.9, 294.0], [33.0, 294.0], [33.1, 294.0], [33.2, 294.0], [33.3, 294.0], [33.4, 294.0], [33.5, 294.0], [33.6, 294.0], [33.7, 294.0], [33.8, 294.0], [33.9, 294.0], [34.0, 294.0], [34.1, 294.0], [34.2, 295.0], [34.3, 295.0], [34.4, 295.0], [34.5, 295.0], [34.6, 295.0], [34.7, 295.0], [34.8, 295.0], [34.9, 295.0], [35.0, 295.0], [35.1, 295.0], [35.2, 295.0], [35.3, 295.0], [35.4, 295.0], [35.5, 295.0], [35.6, 295.0], [35.7, 295.0], [35.8, 295.0], [35.9, 295.0], [36.0, 296.0], [36.1, 296.0], [36.2, 296.0], [36.3, 296.0], [36.4, 296.0], [36.5, 296.0], [36.6, 296.0], [36.7, 296.0], [36.8, 296.0], [36.9, 296.0], [37.0, 296.0], [37.1, 296.0], [37.2, 296.0], [37.3, 296.0], [37.4, 296.0], [37.5, 296.0], [37.6, 296.0], [37.7, 296.0], [37.8, 296.0], [37.9, 297.0], [38.0, 297.0], [38.1, 297.0], [38.2, 297.0], [38.3, 297.0], [38.4, 297.0], [38.5, 297.0], [38.6, 297.0], [38.7, 297.0], [38.8, 297.0], [38.9, 297.0], [39.0, 297.0], [39.1, 297.0], [39.2, 297.0], [39.3, 297.0], [39.4, 297.0], [39.5, 297.0], [39.6, 297.0], [39.7, 297.0], [39.8, 297.0], [39.9, 297.0], [40.0, 298.0], [40.1, 298.0], [40.2, 298.0], [40.3, 298.0], [40.4, 298.0], [40.5, 298.0], [40.6, 298.0], [40.7, 298.0], [40.8, 298.0], [40.9, 298.0], [41.0, 298.0], [41.1, 298.0], [41.2, 298.0], [41.3, 298.0], [41.4, 298.0], [41.5, 298.0], [41.6, 298.0], [41.7, 298.0], [41.8, 299.0], [41.9, 299.0], [42.0, 299.0], [42.1, 299.0], [42.2, 299.0], [42.3, 299.0], [42.4, 299.0], [42.5, 299.0], [42.6, 299.0], [42.7, 299.0], [42.8, 299.0], [42.9, 299.0], [43.0, 299.0], [43.1, 299.0], [43.2, 299.0], [43.3, 299.0], [43.4, 299.0], [43.5, 299.0], [43.6, 299.0], [43.7, 299.0], [43.8, 299.0], [43.9, 300.0], [44.0, 300.0], [44.1, 300.0], [44.2, 300.0], [44.3, 300.0], [44.4, 300.0], [44.5, 300.0], [44.6, 300.0], [44.7, 300.0], [44.8, 300.0], [44.9, 300.0], [45.0, 300.0], [45.1, 300.0], [45.2, 300.0], [45.3, 300.0], [45.4, 300.0], [45.5, 300.0], [45.6, 300.0], [45.7, 300.0], [45.8, 300.0], [45.9, 300.0], [46.0, 301.0], [46.1, 301.0], [46.2, 301.0], [46.3, 301.0], [46.4, 301.0], [46.5, 301.0], [46.6, 301.0], [46.7, 301.0], [46.8, 301.0], [46.9, 301.0], [47.0, 301.0], [47.1, 301.0], [47.2, 301.0], [47.3, 301.0], [47.4, 301.0], [47.5, 301.0], [47.6, 301.0], [47.7, 301.0], [47.8, 301.0], [47.9, 302.0], [48.0, 302.0], [48.1, 302.0], [48.2, 302.0], [48.3, 302.0], [48.4, 302.0], [48.5, 302.0], [48.6, 302.0], [48.7, 302.0], [48.8, 302.0], [48.9, 302.0], [49.0, 302.0], [49.1, 302.0], [49.2, 302.0], [49.3, 302.0], [49.4, 302.0], [49.5, 302.0], [49.6, 302.0], [49.7, 302.0], [49.8, 303.0], [49.9, 303.0], [50.0, 303.0], [50.1, 303.0], [50.2, 303.0], [50.3, 303.0], [50.4, 303.0], [50.5, 303.0], [50.6, 303.0], [50.7, 303.0], [50.8, 303.0], [50.9, 303.0], [51.0, 303.0], [51.1, 303.0], [51.2, 303.0], [51.3, 303.0], [51.4, 303.0], [51.5, 303.0], [51.6, 303.0], [51.7, 303.0], [51.8, 304.0], [51.9, 304.0], [52.0, 304.0], [52.1, 304.0], [52.2, 304.0], [52.3, 304.0], [52.4, 304.0], [52.5, 304.0], [52.6, 304.0], [52.7, 304.0], [52.8, 304.0], [52.9, 304.0], [53.0, 304.0], [53.1, 304.0], [53.2, 304.0], [53.3, 304.0], [53.4, 304.0], [53.5, 304.0], [53.6, 304.0], [53.7, 304.0], [53.8, 304.0], [53.9, 305.0], [54.0, 305.0], [54.1, 305.0], [54.2, 305.0], [54.3, 305.0], [54.4, 305.0], [54.5, 305.0], [54.6, 305.0], [54.7, 305.0], [54.8, 305.0], [54.9, 305.0], [55.0, 305.0], [55.1, 305.0], [55.2, 305.0], [55.3, 305.0], [55.4, 305.0], [55.5, 305.0], [55.6, 305.0], [55.7, 305.0], [55.8, 305.0], [55.9, 306.0], [56.0, 306.0], [56.1, 306.0], [56.2, 306.0], [56.3, 306.0], [56.4, 306.0], [56.5, 306.0], [56.6, 306.0], [56.7, 306.0], [56.8, 306.0], [56.9, 306.0], [57.0, 306.0], [57.1, 306.0], [57.2, 306.0], [57.3, 306.0], [57.4, 306.0], [57.5, 306.0], [57.6, 306.0], [57.7, 306.0], [57.8, 306.0], [57.9, 306.0], [58.0, 307.0], [58.1, 307.0], [58.2, 307.0], [58.3, 307.0], [58.4, 307.0], [58.5, 307.0], [58.6, 307.0], [58.7, 307.0], [58.8, 307.0], [58.9, 307.0], [59.0, 307.0], [59.1, 307.0], [59.2, 307.0], [59.3, 307.0], [59.4, 307.0], [59.5, 307.0], [59.6, 307.0], [59.7, 307.0], [59.8, 308.0], [59.9, 308.0], [60.0, 308.0], [60.1, 308.0], [60.2, 308.0], [60.3, 308.0], [60.4, 308.0], [60.5, 308.0], [60.6, 308.0], [60.7, 308.0], [60.8, 308.0], [60.9, 308.0], [61.0, 308.0], [61.1, 308.0], [61.2, 308.0], [61.3, 308.0], [61.4, 308.0], [61.5, 308.0], [61.6, 309.0], [61.7, 309.0], [61.8, 309.0], [61.9, 309.0], [62.0, 309.0], [62.1, 309.0], [62.2, 309.0], [62.3, 309.0], [62.4, 309.0], [62.5, 309.0], [62.6, 309.0], [62.7, 309.0], [62.8, 309.0], [62.9, 309.0], [63.0, 309.0], [63.1, 309.0], [63.2, 310.0], [63.3, 310.0], [63.4, 310.0], [63.5, 310.0], [63.6, 310.0], [63.7, 310.0], [63.8, 310.0], [63.9, 310.0], [64.0, 310.0], [64.1, 310.0], [64.2, 310.0], [64.3, 310.0], [64.4, 310.0], [64.5, 310.0], [64.6, 310.0], [64.7, 310.0], [64.8, 310.0], [64.9, 311.0], [65.0, 311.0], [65.1, 311.0], [65.2, 311.0], [65.3, 311.0], [65.4, 311.0], [65.5, 311.0], [65.6, 311.0], [65.7, 311.0], [65.8, 311.0], [65.9, 311.0], [66.0, 311.0], [66.1, 311.0], [66.2, 311.0], [66.3, 311.0], [66.4, 311.0], [66.5, 311.0], [66.6, 312.0], [66.7, 312.0], [66.8, 312.0], [66.9, 312.0], [67.0, 312.0], [67.1, 312.0], [67.2, 312.0], [67.3, 312.0], [67.4, 312.0], [67.5, 312.0], [67.6, 312.0], [67.7, 312.0], [67.8, 312.0], [67.9, 312.0], [68.0, 313.0], [68.1, 313.0], [68.2, 313.0], [68.3, 313.0], [68.4, 313.0], [68.5, 313.0], [68.6, 313.0], [68.7, 313.0], [68.8, 313.0], [68.9, 313.0], [69.0, 313.0], [69.1, 313.0], [69.2, 313.0], [69.3, 313.0], [69.4, 313.0], [69.5, 313.0], [69.6, 314.0], [69.7, 314.0], [69.8, 314.0], [69.9, 314.0], [70.0, 314.0], [70.1, 314.0], [70.2, 314.0], [70.3, 314.0], [70.4, 314.0], [70.5, 314.0], [70.6, 314.0], [70.7, 314.0], [70.8, 314.0], [70.9, 314.0], [71.0, 314.0], [71.1, 315.0], [71.2, 315.0], [71.3, 315.0], [71.4, 315.0], [71.5, 315.0], [71.6, 315.0], [71.7, 315.0], [71.8, 315.0], [71.9, 315.0], [72.0, 315.0], [72.1, 315.0], [72.2, 315.0], [72.3, 315.0], [72.4, 315.0], [72.5, 316.0], [72.6, 316.0], [72.7, 316.0], [72.8, 316.0], [72.9, 316.0], [73.0, 316.0], [73.1, 316.0], [73.2, 316.0], [73.3, 316.0], [73.4, 316.0], [73.5, 316.0], [73.6, 316.0], [73.7, 316.0], [73.8, 317.0], [73.9, 317.0], [74.0, 317.0], [74.1, 317.0], [74.2, 317.0], [74.3, 317.0], [74.4, 317.0], [74.5, 317.0], [74.6, 317.0], [74.7, 317.0], [74.8, 317.0], [74.9, 317.0], [75.0, 318.0], [75.1, 318.0], [75.2, 318.0], [75.3, 318.0], [75.4, 318.0], [75.5, 318.0], [75.6, 318.0], [75.7, 318.0], [75.8, 318.0], [75.9, 318.0], [76.0, 318.0], [76.1, 318.0], [76.2, 318.0], [76.3, 319.0], [76.4, 319.0], [76.5, 319.0], [76.6, 319.0], [76.7, 319.0], [76.8, 319.0], [76.9, 319.0], [77.0, 319.0], [77.1, 319.0], [77.2, 319.0], [77.3, 319.0], [77.4, 319.0], [77.5, 320.0], [77.6, 320.0], [77.7, 320.0], [77.8, 320.0], [77.9, 320.0], [78.0, 320.0], [78.1, 320.0], [78.2, 320.0], [78.3, 320.0], [78.4, 320.0], [78.5, 321.0], [78.6, 321.0], [78.7, 321.0], [78.8, 321.0], [78.9, 321.0], [79.0, 321.0], [79.1, 321.0], [79.2, 321.0], [79.3, 321.0], [79.4, 321.0], [79.5, 322.0], [79.6, 322.0], [79.7, 322.0], [79.8, 322.0], [79.9, 322.0], [80.0, 322.0], [80.1, 322.0], [80.2, 322.0], [80.3, 322.0], [80.4, 322.0], [80.5, 323.0], [80.6, 323.0], [80.7, 323.0], [80.8, 323.0], [80.9, 323.0], [81.0, 323.0], [81.1, 323.0], [81.2, 323.0], [81.3, 323.0], [81.4, 324.0], [81.5, 324.0], [81.6, 324.0], [81.7, 324.0], [81.8, 324.0], [81.9, 324.0], [82.0, 324.0], [82.1, 324.0], [82.2, 325.0], [82.3, 325.0], [82.4, 325.0], [82.5, 325.0], [82.6, 325.0], [82.7, 325.0], [82.8, 325.0], [82.9, 325.0], [83.0, 326.0], [83.1, 326.0], [83.2, 326.0], [83.3, 326.0], [83.4, 326.0], [83.5, 326.0], [83.6, 326.0], [83.7, 326.0], [83.8, 327.0], [83.9, 327.0], [84.0, 327.0], [84.1, 327.0], [84.2, 327.0], [84.3, 327.0], [84.4, 328.0], [84.5, 328.0], [84.6, 328.0], [84.7, 328.0], [84.8, 328.0], [84.9, 328.0], [85.0, 329.0], [85.1, 329.0], [85.2, 329.0], [85.3, 329.0], [85.4, 329.0], [85.5, 329.0], [85.6, 329.0], [85.7, 330.0], [85.8, 330.0], [85.9, 330.0], [86.0, 330.0], [86.1, 330.0], [86.2, 330.0], [86.3, 331.0], [86.4, 331.0], [86.5, 331.0], [86.6, 331.0], [86.7, 331.0], [86.8, 331.0], [86.9, 332.0], [87.0, 332.0], [87.1, 332.0], [87.2, 332.0], [87.3, 332.0], [87.4, 333.0], [87.5, 333.0], [87.6, 333.0], [87.7, 333.0], [87.8, 333.0], [87.9, 334.0], [88.0, 334.0], [88.1, 334.0], [88.2, 334.0], [88.3, 334.0], [88.4, 335.0], [88.5, 335.0], [88.6, 335.0], [88.7, 335.0], [88.8, 335.0], [88.9, 336.0], [89.0, 336.0], [89.1, 336.0], [89.2, 336.0], [89.3, 337.0], [89.4, 337.0], [89.5, 337.0], [89.6, 337.0], [89.7, 338.0], [89.8, 338.0], [89.9, 338.0], [90.0, 338.0], [90.1, 339.0], [90.2, 339.0], [90.3, 339.0], [90.4, 339.0], [90.5, 340.0], [90.6, 340.0], [90.7, 340.0], [90.8, 340.0], [90.9, 341.0], [91.0, 341.0], [91.1, 341.0], [91.2, 342.0], [91.3, 342.0], [91.4, 342.0], [91.5, 343.0], [91.6, 343.0], [91.7, 343.0], [91.8, 343.0], [91.9, 344.0], [92.0, 344.0], [92.1, 344.0], [92.2, 345.0], [92.3, 345.0], [92.4, 346.0], [92.5, 346.0], [92.6, 346.0], [92.7, 347.0], [92.8, 347.0], [92.9, 348.0], [93.0, 348.0], [93.1, 348.0], [93.2, 349.0], [93.3, 349.0], [93.4, 349.0], [93.5, 350.0], [93.6, 350.0], [93.7, 351.0], [93.8, 351.0], [93.9, 352.0], [94.0, 352.0], [94.1, 353.0], [94.2, 353.0], [94.3, 354.0], [94.4, 354.0], [94.5, 355.0], [94.6, 355.0], [94.7, 356.0], [94.8, 356.0], [94.9, 357.0], [95.0, 358.0], [95.1, 358.0], [95.2, 359.0], [95.3, 360.0], [95.4, 360.0], [95.5, 361.0], [95.6, 362.0], [95.7, 363.0], [95.8, 364.0], [95.9, 365.0], [96.0, 366.0], [96.1, 367.0], [96.2, 368.0], [96.3, 369.0], [96.4, 370.0], [96.5, 371.0], [96.6, 373.0], [96.7, 374.0], [96.8, 375.0], [96.9, 377.0], [97.0, 379.0], [97.1, 380.0], [97.2, 382.0], [97.3, 384.0], [97.4, 386.0], [97.5, 387.0], [97.6, 390.0], [97.7, 392.0], [97.8, 394.0], [97.9, 397.0], [98.0, 402.0], [98.1, 404.0], [98.2, 407.0], [98.3, 411.0], [98.4, 415.0], [98.5, 421.0], [98.6, 425.0], [98.7, 430.0], [98.8, 437.0], [98.9, 443.0], [99.0, 449.0], [99.1, 460.0], [99.2, 471.0], [99.3, 483.0], [99.4, 501.0], [99.5, 510.0], [99.6, 521.0], [99.7, 532.0], [99.8, 546.0], [99.9, 573.0], [100.0, 679.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
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
        data: {"result": {"minY": 5.0, "minX": 100.0, "maxY": 10486.0, "series": [{"data": [[300.0, 10486.0], [600.0, 5.0], [200.0, 8459.0], [100.0, 38.0], [400.0, 274.0], [500.0, 116.0]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 600.0, "title": "Response Time Distribution"}},
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
        data: {"result": {"minY": 119.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 19259.0, "series": [{"data": [[0.0, 19259.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 119.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 1.0, "title": "Synthetic Response Times Distribution"}},
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
        data: {"result": {"minY": 19.013787510137877, "minX": 1.75038714E12, "maxY": 20.0, "series": [{"data": [[1.75038714E12, 19.013787510137877], [1.75038744E12, 19.92040217846669], [1.75038732E12, 20.0], [1.75038738E12, 20.0], [1.7503872E12, 20.0], [1.75038726E12, 20.0]], "isOverall": false, "label": "线程组", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75038744E12, "title": "Active Threads Over Time"}},
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
        data: {"result": {"minY": 171.88888888888889, "minX": 1.0, "maxY": 493.1, "series": [{"data": [[2.0, 225.33333333333334], [8.0, 225.11111111111111], [9.0, 255.625], [10.0, 254.45454545454547], [11.0, 271.2], [3.0, 186.83333333333331], [12.0, 295.7], [13.0, 317.40000000000003], [14.0, 326.2727272727273], [15.0, 364.3999999999999], [4.0, 202.8], [16.0, 373.5833333333333], [1.0, 310.0], [17.0, 382.12500000000006], [18.0, 432.0], [19.0, 493.1], [5.0, 217.875], [20.0, 307.16506218452497], [6.0, 171.88888888888889], [7.0, 180.45454545454544]], "isOverall": false, "label": "HTTP请求配置", "isController": false}, {"data": [[19.927443492620473, 307.074620703894]], "isOverall": false, "label": "HTTP请求配置-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 20.0, "title": "Time VS Threads"}},
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
        data : {"result": {"minY": 5064.45, "minX": 1.75038714E12, "maxY": 48719.666666666664, "series": [{"data": [[1.75038714E12, 14487.75], [1.75038744E12, 28037.583333333332], [1.75038732E12, 45735.166666666664], [1.75038738E12, 45382.666666666664], [1.7503872E12, 48719.666666666664], [1.75038726E12, 45241.666666666664]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.75038714E12, 5064.45], [1.75038744E12, 9804.65], [1.75038732E12, 15994.9], [1.75038738E12, 15871.7], [1.7503872E12, 17038.366666666665], [1.75038726E12, 15822.366666666667]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75038744E12, "title": "Bytes Throughput Over Time"}},
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
        data: {"result": {"minY": 288.77603664416546, "minX": 1.75038714E12, "maxY": 333.79967558799655, "series": [{"data": [[1.75038714E12, 333.79967558799655], [1.75038744E12, 310.60242982823564], [1.75038732E12, 307.9820236260917], [1.75038738E12, 310.48343685300244], [1.7503872E12, 288.77603664416546], [1.75038726E12, 311.70197300103945]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75038744E12, "title": "Response Time Over Time"}},
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
        data: {"result": {"minY": 288.6133076181287, "minX": 1.75038714E12, "maxY": 333.70478507704786, "series": [{"data": [[1.75038714E12, 333.70478507704786], [1.75038744E12, 310.43988269794755], [1.75038732E12, 307.7886492039034], [1.75038738E12, 310.2890786749483], [1.7503872E12, 288.6133076181287], [1.75038726E12, 311.5051921079951]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75038744E12, "title": "Latencies Over Time"}},
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
        data: {"result": {"minY": 0.00879765395894429, "minX": 1.75038714E12, "maxY": 0.03730738037307376, "series": [{"data": [[1.75038714E12, 0.03730738037307376], [1.75038744E12, 0.00879765395894429], [1.75038732E12, 0.01027221366204417], [1.75038738E12, 0.010351966873706028], [1.7503872E12, 0.009643201542912275], [1.75038726E12, 0.010903426791277265]], "isOverall": false, "label": "HTTP请求配置", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75038744E12, "title": "Connect Time Over Time"}},
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
        data: {"result": {"minY": 130.0, "minX": 1.75038714E12, "maxY": 679.0, "series": [{"data": [[1.75038714E12, 600.0], [1.75038744E12, 550.0], [1.75038732E12, 562.0], [1.75038738E12, 583.0], [1.7503872E12, 612.0], [1.75038726E12, 679.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.75038714E12, 444.60000000000014], [1.75038744E12, 336.0], [1.75038732E12, 333.0], [1.75038738E12, 340.0], [1.7503872E12, 318.0], [1.75038726E12, 342.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.75038714E12, 574.6600000000001], [1.75038744E12, 374.1199999999999], [1.75038732E12, 383.0500000000002], [1.75038738E12, 400.0], [1.7503872E12, 365.02000000000044], [1.75038726E12, 435.4699999999998]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.75038714E12, 488.29999999999995], [1.75038744E12, 348.0], [1.75038732E12, 346.0], [1.75038738E12, 356.0], [1.7503872E12, 329.0], [1.75038726E12, 365.0]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.75038714E12, 132.0], [1.75038744E12, 136.0], [1.75038732E12, 164.0], [1.75038738E12, 130.0], [1.7503872E12, 139.0], [1.75038726E12, 259.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.75038714E12, 320.0], [1.75038744E12, 308.0], [1.75038732E12, 304.0], [1.75038738E12, 305.0], [1.7503872E12, 284.0], [1.75038726E12, 305.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75038744E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
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
    data: {"result": {"minY": 182.0, "minX": 6.0, "maxY": 534.0, "series": [{"data": [[35.0, 341.0], [36.0, 266.5], [37.0, 534.0], [38.0, 362.5], [47.0, 425.0], [48.0, 415.0], [51.0, 370.0], [54.0, 369.0], [55.0, 346.0], [56.0, 344.0], [57.0, 348.0], [58.0, 329.0], [59.0, 340.0], [60.0, 325.5], [61.0, 317.0], [62.0, 318.0], [63.0, 314.0], [65.0, 304.0], [67.0, 300.0], [66.0, 302.0], [64.0, 307.0], [71.0, 281.0], [70.0, 281.0], [68.0, 299.0], [69.0, 293.0], [73.0, 274.0], [74.0, 270.0], [72.0, 279.0], [76.0, 271.5], [6.0, 307.0], [14.0, 182.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 76.0, "title": "Response Time Vs Request"}},
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
    data: {"result": {"minY": 182.0, "minX": 6.0, "maxY": 534.0, "series": [{"data": [[35.0, 340.5], [36.0, 265.5], [37.0, 534.0], [38.0, 362.0], [47.0, 425.0], [48.0, 415.0], [51.0, 368.0], [54.0, 368.5], [55.0, 345.0], [56.0, 344.0], [57.0, 348.0], [58.0, 329.0], [59.0, 340.0], [60.0, 325.5], [61.0, 317.0], [62.0, 318.0], [63.0, 314.0], [65.0, 304.0], [67.0, 300.0], [66.0, 301.0], [64.0, 306.0], [71.0, 281.0], [70.0, 281.0], [68.0, 299.0], [69.0, 293.0], [73.0, 274.0], [74.0, 270.0], [72.0, 279.0], [76.0, 271.0], [6.0, 307.0], [14.0, 182.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 76.0, "title": "Latencies Vs Request"}},
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
        data: {"result": {"minY": 20.883333333333333, "minX": 1.75038714E12, "maxY": 69.13333333333334, "series": [{"data": [[1.75038714E12, 20.883333333333333], [1.75038744E12, 39.45], [1.75038732E12, 64.9], [1.75038738E12, 64.4], [1.7503872E12, 69.13333333333334], [1.75038726E12, 64.2]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75038744E12, "title": "Hits Per Second"}},
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
        data: {"result": {"minY": 20.55, "minX": 1.75038714E12, "maxY": 69.13333333333334, "series": [{"data": [[1.75038714E12, 20.55], [1.75038744E12, 39.78333333333333], [1.75038732E12, 64.9], [1.75038738E12, 64.4], [1.7503872E12, 69.13333333333334], [1.75038726E12, 64.2]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.75038744E12, "title": "Codes Per Second"}},
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
        data: {"result": {"minY": 20.55, "minX": 1.75038714E12, "maxY": 69.13333333333334, "series": [{"data": [[1.75038714E12, 20.55], [1.75038744E12, 39.78333333333333], [1.75038732E12, 64.9], [1.75038738E12, 64.4], [1.7503872E12, 69.13333333333334], [1.75038726E12, 64.2]], "isOverall": false, "label": "HTTP请求配置-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75038744E12, "title": "Transactions Per Second"}},
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
        data: {"result": {"minY": 20.55, "minX": 1.75038714E12, "maxY": 69.13333333333334, "series": [{"data": [[1.75038714E12, 20.55], [1.75038744E12, 39.78333333333333], [1.75038732E12, 64.9], [1.75038738E12, 64.4], [1.7503872E12, 69.13333333333334], [1.75038726E12, 64.2]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.75038744E12, "title": "Total Transactions Per Second"}},
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

