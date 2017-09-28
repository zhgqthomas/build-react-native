import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {
    View,
    requireNativeComponent
} from 'react-native';

class CustomView extends Component {

    constructor(props) {
        super(props);
        this._onChange = this._onChange.bind(this);
        this._onMapPress = this._onMapPress.bind(this);
    }

    _onChange(event) {
        alert("onChange");
    }

    _onSelect(event) {
        alert("onSelect");
    }

    _onMapPress(event) {
        alert("onMapPress");
    }

    render() {
        return (
            <RCTCustomView
                {...this.props}
                onChange={this._onChange}
                onSelect={this._onSelect}
                onMapPress={this._onMapPress}
            />
        )
    }
}

CustomView.propTypes = {
    ...View.propTypes,
    onChange: PropTypes.func,
    onMapPress: PropTypes.func
};

const RCTCustomView = requireNativeComponent('RCTCustomView', CustomView);

module.exports = CustomView;

