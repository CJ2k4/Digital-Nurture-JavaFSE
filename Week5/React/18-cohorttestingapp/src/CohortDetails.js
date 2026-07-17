import React, { Component } from 'react';

// Component under test. Displays a single cohort's details.
// The cohort code is rendered in an <h3> element.
class CohortDetails extends Component {
  render() {
    const { cohort } = this.props;
    const headingColor = cohort.status === 'ongoing' ? 'green' : 'blue';

    return (
      <div className="box">
        <h3 style={{ color: headingColor }}>{cohort.cohortCode}</h3>
        <dl>
          <dt>Technology</dt>
          <dd>{cohort.technology}</dd>
          <dt>Trainer</dt>
          <dd>{cohort.trainer}</dd>
          <dt>Status</dt>
          <dd>{cohort.status}</dd>
        </dl>
      </div>
    );
  }
}

export default CohortDetails;
