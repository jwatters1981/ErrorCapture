var chartColours = [ '#88bbc8', '#ed7a53', '#9FC569', '#bbdce3', '#9a3b1b',
		'#5a8022', '#2c7282' ];
var FlotController = function($scope, $http) {

	randNum = function() {
		// return Math.floor(Math.random()*101);
		return (Math.floor(Math.random() * (1 + 40 - 20))) + 20;
	}
	// some data
	var d1 = [ [ 1, 3 + randNum() ], [ 2, 6 + randNum() ],
			[ 3, 9 + randNum() ], [ 4, 12 + randNum() ], [ 5, 15 + randNum() ],
			[ 6, 18 + randNum() ], [ 7, 21 + randNum() ],
			[ 8, 15 + randNum() ], [ 9, 18 + randNum() ],
			[ 10, 21 + randNum() ], [ 11, 24 + randNum() ],
			[ 12, 27 + randNum() ], [ 13, 30 + randNum() ],
			[ 14, 33 + randNum() ], [ 15, 24 + randNum() ],
			[ 16, 27 + randNum() ], [ 17, 30 + randNum() ],
			[ 18, 33 + randNum() ], [ 19, 36 + randNum() ],
			[ 20, 39 + randNum() ], [ 21, 42 + randNum() ],
			[ 22, 45 + randNum() ], [ 23, 36 + randNum() ],
			[ 24, 39 + randNum() ], [ 25, 42 + randNum() ],
			[ 26, 45 + randNum() ], [ 27, 38 + randNum() ],
			[ 28, 51 + randNum() ], [ 29, 55 + randNum() ],
			[ 30, 60 + randNum() ] ];
	var d2 = [ [ 1, randNum() - 5 ], [ 2, randNum() - 4 ],
			[ 3, randNum() - 4 ], [ 4, randNum() ], [ 5, 4 + randNum() ],
			[ 6, 4 + randNum() ], [ 7, 5 + randNum() ], [ 8, 5 + randNum() ],
			[ 9, 6 + randNum() ], [ 10, 6 + randNum() ], [ 11, 6 + randNum() ],
			[ 12, 2 + randNum() ], [ 13, 3 + randNum() ],
			[ 14, 4 + randNum() ], [ 15, 4 + randNum() ],
			[ 16, 4 + randNum() ], [ 17, 5 + randNum() ],
			[ 18, 5 + randNum() ], [ 19, 2 + randNum() ],
			[ 20, 2 + randNum() ], [ 21, 3 + randNum() ],
			[ 22, 3 + randNum() ], [ 23, 3 + randNum() ],
			[ 24, 2 + randNum() ], [ 25, 4 + randNum() ],
			[ 26, 4 + randNum() ], [ 27, 5 + randNum() ],
			[ 28, 2 + randNum() ], [ 29, 2 + randNum() ], [ 30, 3 + randNum() ] ];
	// define placeholder class
	// graph options
	var options = {
		grid : {
			show : true,
			aboveData : true,
			color : "#3f3f3f",
			labelMargin : 5,
			axisMargin : 0,
			borderWidth : 0,
			borderColor : null,
			minBorderMargin : 5,
			clickable : true,
			hoverable : true,
			autoHighlight : true,
			mouseActiveRadius : 20
		},
		series : {
			grow : {
				active : false,
				stepMode : "linear",
				steps : 50,
				stepDelay : true
			},
			lines : {
				show : true,
				fill : true,
				lineWidth : 4,
				steps : false
			},
			points : {
				show : true,
				radius : 5,
				symbol : "circle",
				fill : true,
				borderColor : "#fff"
			}
		},
		legend : {
			position : "ne",
			margin : [ 0, -25 ],
			noColumns : 0,
			labelBoxBorderColor : null,
			labelFormatter : function(label, series) {
				// just add some space to labes
				return label + '&nbsp;&nbsp;';
			}
		},
		yaxis : {
			min : 0
		},
		xaxis : {
			ticks : 11,
			tickDecimals : 0
		},
		colors : chartColours,
		shadowSize : 1,
		tooltip : true, // activate tooltip
		tooltipOpts : {
			content : "%s : %y.0",
			shifts : {
				x : -30,
				y : -50
			}
		}
	};
	$scope.options = options;
	$scope.data = [ {
		label : "Visits",
		data : d1,
		lines : {
			fillColor : "#f2f7f9"
		},
		points : {
			fillColor : "#88bbc8"
		}
	}, {
		label : "Unique Visits",
		data : d2,
		lines : {
			fillColor : "#fff8f2"
		},
		points : {
			fillColor : "#ed7a53"
		}
	} ]
};

var PieController = function($scope, $http) {

	var data = [ {
		label : "%78.75 Open Tickts",
		data : 78.75,
		color : "#88bbc8"
	}, {
		label : "%21.25 Closed Tickets",
		data : 21.25,
		color : "#ed7a53"
	} ];

	$scope.data = data;
	$scope.options = {
		series : {
			pie : {
				show : true,
				highlight : {
					opacity : 0.1
				},
				stroke : {
					color : '#fff',
					width : 3
				},
				startAngle : 2,
				label : {
					radius : 1
				}
			},
			grow : {
				active : false
			}
		},
		legend : {
			position : "ne",
			labelBoxBorderColor : null
		},
		grid : {
			hoverable : true,
			clickable : true
		},
		tooltip : true, // activate tooltip
		tooltipOpts : {
			content : "%s : %y.1",
			shifts : {
				x : -30,
				y : -50
			}
		}
	};

};

var MonthlyChartController = function($scope, $http) {

	// some data
	var d1 = [];
	for (var i = 0; i <= 10; i += 1)
		d1.push([ i, parseInt(Math.random() * 30) ]);

	var d2 = [];
	for (var i = 0; i <= 10; i += 1)
		d2.push([ i, parseInt(Math.random() * 30) ]);

	var d3 = [];
	for (var i = 0; i <= 10; i += 1)
		d3.push([ i, parseInt(Math.random() * 30) ]);

	var ds = new Array();

	ds.push({
		label : "Data One",
		data : d1,
		bars : {
			order : 1
		}
	});
	ds.push({
		label : "Data Two",
		data : d2,
		bars : {
			order : 2
		}
	});
	ds.push({
		label : "Data Three",
		data : d3,
		bars : {
			order : 3
		}
	});

	var options = {
		bars : {
			show : true,
			barWidth : 0.2,
			fill : 1
		},
		grid : {
			show : true,
			aboveData : false,
			color : "#3f3f3f",
			labelMargin : 5,
			axisMargin : 0,
			borderWidth : 0,
			borderColor : null,
			minBorderMargin : 5,
			clickable : true,
			hoverable : true,
			autoHighlight : false,
			mouseActiveRadius : 20
		},
		series : {
			grow : {
				active : false
			}
		},
		legend : {
			position : "ne"
		},
		colors : chartColours,
		tooltip : true, // activate tooltip
		tooltipOpts : {
			content : "%s : %y.0",
			shifts : {
				x : -30,
				y : -50
			}
		}
	};

	$scope.data = ds;
	$scope.options = options;
};

// App.controller('barChartController', function($scope){
//    
// var daftPoints = [[0, 4]],
// punkPoints = [[1, 20]];
//    
// var data1 = [
// {
// data: daftPoints,
// color: '#00b9d7',
// bars: {show: true, barWidth:1, fillColor: '#00b9d7', order: 1, align:
// "center" }
// },
// {
// data: punkPoints,
// color: '#3a4452',
// bars: {show: true, barWidth:1, fillColor: '#3a4452', order: 2, align:
// "center" }
// }
// ];
//    
// $scope.data = data1;
//    
//   
// });
