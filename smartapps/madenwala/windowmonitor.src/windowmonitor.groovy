/**
 *  Window Monitor
 *
 *  Copyright 2019 Mohammed Adenwala
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Window Monitor",
    namespace: "madenwala",
    author: "Mohammed Adenwala",
    description: "Hour weather forcasts to detect upcoming precipitation and remind you to close any open windows.",
    category: "My Apps",
	iconUrl:   "https://raw.githubusercontent.com/madenwala/SmartThings-WindowMonitor/master/icons/Icon.png",
	iconX2Url: "https://raw.githubusercontent.com/madenwala/SmartThings-WindowMonitor/master/icons/Icon@2x.png",
	iconX3Url: "https://raw.githubusercontent.com/madenwala/SmartThings-WindowMonitor/master/icons/Icon@3x.png")

preferences {
    section("Personalization") {
    	paragraph "Test"
        input "personName", "text", required: true, title: "Name"
    }
    /*
    section("Sensors") {
        input "sensors", "capability.contactSensor", title: "Windows to Monitor", multiple: true, required: false
    }
    section("Device Notifications") {
    	input "enableNotifications", "boolean", title: "Send push notifications", defaultValue: true
    }
    section("Audio Notifications") {
        input "audioSpeakers", "capability.audioNotification", title: "Audio Devices", multiple: true, required: false
        input "alexaSpeakers", "device.echoSpeaksDevice", title: "Alexa Devices", multiple: true, required: false
    }
    section("Settings") {
    	input "accuweatherApiKey", "text", title: "AccuWeather API Key", required: true, defaultValue: "IDAqoGCKyaIPlMgvr4dGjIos8uOTLqqA"
        input "locationKey", "text", title: "Location Key", required: true, defaultValue: "26448_PC"
    }*/
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	initialize()
}

//def APP_NAME = "WindowMonitor"

def initialize() {
	//state.APP_NAME = "WindowMonitor"
    //state.LAST_RUN = new Date()
    //subscribe(app, appHandler)
    //subscribe(sensors, "contact.open", eventHandler)
    //runEvery1Minute(refreshData)
    //runEvery1Hour(refreshData)
}

/*

def appHandler(evt) {
	log.debug state.APP_NAME + ": App Event ${evt.value} received"
    refreshData(true)
}

def eventHandler(evt) {
	log.debug "Sensor opened: ${evt}"
    refreshData(false)
}

def refreshData() {
	refreshData(false)
}

def refreshData(isAppHandler){
	try {
        log.info state.APP_NAME + ": Refresh data..."
        def openSenors = getOpenSensors()

        if(openSensors != null && openSensors.isEmpty() == false) {
            log.debug state.APP_NAME + ": ${openSensors} are open"
            def data = getData()
            def message = getMessage(data, isAppHandler)
            notifications(message)
        } else {
            log.debug state.APP_NAME + ": All contact sensors are closed."
            if(isAppHandler) {
            	notifications("All windows and doors are closed.")
            }
        }
    } catch(ex) {
    	log.error state.APP_NAME + ": Could not refreshData: " + ex
    }
}

def getOpenSensors() {
	def openSensors = null
    openSensors = sensors.findAll {
        it.currentContact == "open"
    }
    return openSensors
}

def getData() {
	try{
        def jsonUrl = "https://dataservice.accuweather.com/forecasts/v1/hourly/12hour/26448_PC?apikey=IDAqoGCKyaIPlMgvr4dGjIos8uOTLqqA"
        //def jsonUrl = "https://dataservice.accuweather.com/forecasts/v1/hourly/1hour/26448_PC?apikey=IDAqoGCKyaIPlMgvr4dGjIos8uOTLqqA"
        log.debug state.APP_NAME + ": Refresh Data from ${jsonUrl}"

        def params = [
            uri: jsonUrl,
            contentType: 'application/json'
        ]

        httpGet(params) { resp ->
            if(resp.status == 200){
                // get the data from the response body
                //log.debug state.APP_NAME + ": Data returned!"
                log.debug state.APP_NAME + ": Data: ${resp.data[0]}"
                return resp.data;
            } else {
                // get the status code of the response
                log.debug state.APP_NAME + ": Status Code: ${resp.status}"
                return null;
            }  
        }
    } catch(e) {
        if (e.equals("groovyx.net.http.ResponseParseException: Unauthorized")) {
            log.debug "User unauthorized, requesting new token"
             // do something
        }
        else {
            log.error "Something went wrong with the AccuWeather API call $e"
        }
        return null;
    }
}

def getMessage(data, isAppHandler) {
	try {
        def message = null
        if(data == null || data.length == 0){
            message = "I can't tell if it's going to rain because I could not access AccuWeather data."
            log.error message    
        } else if(data.size() >= 1 && (data[0].HasPrecipitation || data[0].PrecipitationProbability > 0)) {
            message = "There is a ${data[0].PrecipitationProbability}% chance of rain within the hour."
        } else if(data.size() >= 2 && (data[1].HasPrecipitation || data[1].PrecipitationProbability > 0)) {
            message = "There is a ${data[1].PrecipitationProbability}% chance of rain after an hour."
        } else if(isAppHandler) {
            message = "No rain in the forecast. You're welcome to open windows or doors."
        } else {
            log.info "No rain in the current forecast."
        }
        log.debug "Message: " + message;
        return message;
    }
    catch(ex) {
    	log.error state.APP_NAME + ": Could run getMessage: " + ex
        return null
    }
}

def notifications(message) {
    log.info state.APP_NAME + ": Audio Speak: " + message;
    if(message) {
    	try {
            audioSpeakers*.playTextAndRestore(message)
            alexaSpeakers*.playAnnouncement(message)
        } catch(ex) {
    		log.error state.APP_NAME + ": Could play audio on speakers: " + ex
        }        
		if(sendPush && enableNotifications == 'true')
    		sendPush(message)
    }
}
*/