/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';
import {NativeModules} from 'react-native';


AppRegistry.registerComponent(appName, () => App);

const HelloWorld = NativeModules.HelloWorld;
export default HelloWorld
export const openExampleActivity = () => {
  console.log(HelloWorld.showMessage("text"));
};