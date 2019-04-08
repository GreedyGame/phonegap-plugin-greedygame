function GreedyGameAgent() {
	console.log('Device platform', device.platform);	
}

function isAndroid() {
	return device.platform == "Android";
}

GreedyGameAgent.prototype.gameId = function(gameId) {
	console.log('Game id: ', gameId);
	if(isAndroid()) {
		GreedyGamePlugin.gameId(gameId);
	}
	return this;
};

GreedyGameAgent.prototype.setAdmobEnabled = function(admobEnabled) {
	console.log('Admob Enabled: ', admobEnabled);
	if(isAndroid()) {
		GreedyGamePlugin.admob(admobEnabled);	
	}
	return this;
};

GreedyGameAgent.prototype.setMopubEnabled = function(mopubEnabled) {
	console.log('Mopub Enabled: ', mopubEnabled);
	if(isAndroid()) {
		GreedyGamePlugin.mopub(mopubEnabled);
	}
	return this;
};

GreedyGameAgent.prototype.setFacebookEnabled = function(facebookEnabled) {
	console.log('Facebook Enabled: ', facebookEnabled);
	if(isAndroid()) {
		GreedyGamePlugin.facebook(facebookEnabled);
	}
	return this;
};

GreedyGameAgent.prototype.setNpa = function(npa) {
	console.log('Npa: ', npa);
	if(isAndroid()) {
		GreedyGamePlugin.npa(npa);
	}
	return this;
};

GreedyGameAgent.prototype.coppa = function(coppa) {
	console.log('Coppa: ', coppa);
	if(isAndroid()) {
		GreedyGamePlugin.coppa(coppa);
	}
	return this;
};

GreedyGameAgent.prototype.addUnits = function(units) {
	console.log('Add units: ', units);
	if(isAndroid()) {
		GreedyGamePlugin.units(units);
	}
	return this;
};

GreedyGameAgent.prototype.init = function(successCallback, failureCallback) {
	if(!isAndroid()) {
		console.log('GreedyGame SDK will only be initialized on Android Platform')
		return this;
	}
	GreedyGamePlugin.gameEngine('Cordova');
	GreedyGamePlugin.engineVersion('1.2.3');
	GreedyGamePlugin.init(function(result) {
		console.log('Received init result', result);
		if(result.status == "success") {
			successCallback(result.data);
		} else if(result.status == "failure") {
			failureCallback(result.data);
		}
	});
	return this;
};

GreedyGameAgent.prototype.refresh = function() {
	console.log('Refreshing ad');
	if(isAndroid()) {
		GreedyGamePlugin.refresh();
	}
};

GreedyGameAgent.prototype.getPath = function(unitId, callback) {
	console.log('Get Path for unit: ' + unitId);
	if(isAndroid()) {
		GreedyGamePlugin.getPath(unitId, callback);
	}
};

GreedyGameAgent.prototype.showUii = function(unitId) {
	console.log('Show uii for unit: ' + unitId);
	if(isAndroid()) {
		GreedyGamePlugin.showUii(unitId);
	}
};