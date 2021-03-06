package inesc_id.pt.motivandroid.onboarding.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import inesc_id.pt.motivandroid.R;
import inesc_id.pt.motivandroid.data.Campaign;
import inesc_id.pt.motivandroid.onboarding.wrappers.CampaignWrapper;

/**
 *  CampaignListAdapter
 *
 *      Adapter to draw a list of campaigns, with a checkbox for the user to select which ones he/she
 *      wants to enrolled in.
 *
 * (C) 2017-2020 - The Woorti app is a research (non-commercial) application that was
 * developed in the context of the European research project MoTiV (motivproject.eu). The
 * code was developed by partner INESC-ID with contributions in graphics design by partner
 * TIS. The Woorti app development was one of the outcomes of a Work Package of the MoTiV
 * project.
 * The Woorti app was originally intended as a tool to support data collection regarding
 * mobility patterns from city and country-wide campaigns and provide the data and user
 * management to campaign managers.
 *
 * The Woorti app development followed an agile approach taking into account ongoing
 * feedback of partners and testing users while continuing under development. This has
 * been carried out as an iterative process deploying new app versions. Along the
 * timeline, various previously unforeseen requirements were identified, some requirements
 * Were revised, there were requests for modifications, extensions, or new aspects in
 * functionality or interaction as found useful or interesting to campaign managers and
 * other project partners. Most stemmed naturally from the very usage and ongoing testing
 * of the Woorti app. Hence, code and data structures were successively revised in a
 * way not only to accommodate this but, also importantly, to maintain compatibility with
 * the functionality, data and data structures of previous versions of the app, as new
 * version roll-out was never done from scratch.
 * The code developed for the Woorti app is made available as open source, namely to
 * contribute to further research in the area of the MoTiV project, and the app also makes
 * use of open source components as detailed in the Woorti app license.
 * This project has received funding from the European Union’s Horizon 2020 research and
 * innovation programme under grant agreement No. 770145.
 * This file is part of the Woorti app referred to as SOFTWARE.
 */
public class CampaignListAdapter extends ArrayAdapter<CampaignWrapper> {

    private ArrayList<CampaignWrapper> dataSet = new ArrayList<>();
    private Context mContext;

    private static class ViewHolder {
        CheckBox isSelectedCheckBox;
        TextView titleTextView;
        TextView textTextView;

    }

    public CampaignListAdapter(ArrayList<CampaignWrapper> data, Context context) {
        super(context, R.layout.onboarding_campaigns_listitem, data);
        //this.dataSet.addAll(data);

        this.mContext = context;

        this.dataSet = data;

    }

    @Override
    public int getViewTypeCount() {
        return dataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        final CampaignWrapper dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.onboarding_campaigns_listitem, parent, false);

            viewHolder.isSelectedCheckBox = (CheckBox) convertView.findViewById(R.id.isCampaignSelectedCheckBox);
            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.campaignTitleTextView);
            viewHolder.titleTextView.setText(dataModel.getCampaign().getName());


            viewHolder.textTextView = (TextView) convertView.findViewById(R.id.campaignTextTextView);



            viewHolder.isSelectedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // update your model (or other business logic) based on isChecked
                    Log.e("checkbox", "isChecked" + isChecked + " at " + position);
                    dataModel.setSelected(isChecked);
                }
            });


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    public ArrayList<Campaign> getCampaignsSelected() {

        ArrayList<Campaign> result = new ArrayList<>();

        for(CampaignWrapper mode : dataSet){
            if(mode.isSelected()) result.add(mode.getCampaign());
        }

        return result;
    }
}