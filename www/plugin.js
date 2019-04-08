var exec = require('cordova/exec');

var PLUGIN_NAME = "GreedyGamePlugin";

var GreedyGamePlugin = {
	init: function (cb) {
		exec(cb, null, PLUGIN_NAME, 'init', []);
	},
	getPath: function(phrase, cb) {
		exec(cb, null, PLUGIN_NAME, 'getPath', [phrase]);
	},
	gameId: function(gameId) {
		exec(null, null, PLUGIN_NAME, 'gameId', [gameId]);
	},
	admob: function (phrase) {
		exec(null, null, PLUGIN_NAME, 'admob', [phrase]);
	},
	coppa: function (phrase) {
		exec(null, null, PLUGIN_NAME, 'coppa', [phrase]);
	},
	mopub: function (phrase) {
		exec(null, null, PLUGIN_NAME, 'mopub', [phrase]);
	},
	facebook: function (phrase) {
		exec(null, null, PLUGIN_NAME, 'facebook', [phrase]);
	},
	npa: function (phrase) {
		exec(null, null, PLUGIN_NAME, 'npa', [phrase]);
	},
	refresh: function (phrase) {
		exec(null, null, PLUGIN_NAME, 'refresh', [phrase]);
	},
	sendCrash: function (phrase) {
		exec(null, null, PLUGIN_NAME, 'sendCrash', [phrase]);
	},
	gameEngine: function (phrase) {
		exec(null, null, PLUGIN_NAME, 'gameEngine', [phrase]);
	},
	engineVersion: function (phrase) {
		exec(null, null, PLUGIN_NAME, 'engineVersion', [phrase]);
	},
	units: function(phrase) {
		exec(null, null, PLUGIN_NAME, 'units', [phrase]);
	},
	showUii: function(phrase) {
		exec(null, null, PLUGIN_NAME, 'showUii', [phrase]);	
	},
};

module.exports = GreedyGamePlugin;